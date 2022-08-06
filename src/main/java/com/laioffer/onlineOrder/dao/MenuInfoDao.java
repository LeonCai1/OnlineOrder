package com.laioffer.onlineOrder.dao;

import com.laioffer.onlineOrder.entity.MenuItem;
import com.laioffer.onlineOrder.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;
@Repository
public class MenuInfoDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Restaurant> getRestaurants(){
        Session session = null;
        try  {
            session=sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
            // select * from
            criteria.from(Restaurant.class);
            //return the whole table
            return session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if(session !=null){
                session.close();
            }
        }

        return new ArrayList<>();

    }
    public List<MenuItem>getAllMenuItem(int restaurantId){
        try (Session session = sessionFactory.openSession()) {
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null) {
                return restaurant.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();


    }
    public MenuItem getMenuItem(int menuItemId){
        Session session = null;
        try{
            session = sessionFactory.openSession();
                return session.get(MenuItem.class, menuItemId);
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            if(session!=null){
                session.close();
            }
        }
        return null;
    }
}
