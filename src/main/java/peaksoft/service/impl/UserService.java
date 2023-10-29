package peaksoft.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.model.User;
import peaksoft.service.ModelService;

import java.util.List;

@Service
@Transactional
public class UserService implements ModelService<User> {
    /**
     * @PersistenceContext: Эта аннотация говорит Spring о том, что нужно внедрить EntityManager в это поле.
     * EntityManager управляет жизненным циклом объектов-сущностей и выполняет операции базы данных, такие как сохранение,
     * обновление, удаление и запросы.
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class,id);
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = entityManager.createQuery("from User").getResultList();
        return users;
    }

    @Override
    public void update(Long id, User user) {
        User oldUser = findById(id);
        oldUser.setName(user.getName());
        oldUser.setAge(user.getAge());
        entityManager.merge(oldUser);
    }

    @Override
    public void deleteById(Long id) {
//        entityManager.createQuery("DELETE User u where u.id=?1").setParameter(1,id);
                entityManager.remove(findById(id));
    }
}
