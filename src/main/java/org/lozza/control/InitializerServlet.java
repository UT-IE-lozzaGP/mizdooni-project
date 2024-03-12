package org.lozza.control;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lozza.business.entry.utils.Address;
import org.lozza.business.services.RestaurantService;
import org.lozza.business.services.ReviewService;
import org.lozza.business.services.TableService;
import org.lozza.business.services.UserService;
import org.lozza.business.services.exceptions.RestaurantServiceException;
import org.lozza.business.services.exceptions.ReviewServiceException;
import org.lozza.business.services.exceptions.TableServiceException;
import org.lozza.business.services.exceptions.UserServiceException;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalTime;

@WebServlet("/init")
public class InitializerServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(InitializerServlet.class);
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Class<?> clazz = InitializerServlet.class;

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("doGet")) continue;
            if (!java.lang.reflect.Modifier.isStatic(method.getModifiers())) {
                try {
                    method.setAccessible(true);
                    method.invoke(this);
                } catch (Exception ignored) {
                }
            }
        }

        response.sendRedirect(request.getContextPath() + "/");
    }

    protected void add_clients() throws UserServiceException {
        logger.info("adding clients");
        UserService.addUser(
                "client",
                "test_client",
                "123",
                "client_test@gmail.com",
                new Address(
                        "Iran",
                        "Tehran",
                        "Azadi"
                ));
        UserService.addUser(
                "client",
                "test_client2",
                "123",
                "client_test2@gmail.com",
                new Address(
                        "Iran",
                        "Bokhara"
                ));
    }
    protected void add_managers() throws UserServiceException {
        logger.info("adding managers");
        UserService.addUser(
                "manager",
                "test_manager",
                "123",
                "manager_test@gmail.com",
                new Address(
                        "Iran",
                        "Behshahr",
                        "Bolvar"
                ));
    }

    protected void add_restaurants() throws UserServiceException, RestaurantServiceException {
        logger.info("adding restaurants");
        RestaurantService.addRestaurant(
                "test_restaurant 1",
                UserService.getManagerByForce("test_manager"),
                "Iranian",
                "test Iranian restaurant",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0),
                new Address(
                        "Iran",
                        "Behdasht",
                        "test"
                )
        );
        RestaurantService.addRestaurant(
                "test_restaurant 2",
                UserService.getManagerByForce("test_manager"),
                "Thai",
                "test Thai restaurant",
                LocalTime.of(10, 0),
                LocalTime.of(17, 0),
                new Address(
                        "Iran",
                        "Tehran",
                        "Bandari"
                )
        );
        RestaurantService.addRestaurant(
                "test_restruanat 3",
                UserService.getManagerByForce("test_manager"),
                "Thai",
                "test Thai restaurant",
                LocalTime.of(15, 0),
                LocalTime.of(23, 0),
                new Address(
                        "Iran",
                        "Tehran",
                        "Keshavarz"
                )
        );
    }

    protected void add_tables() throws RestaurantServiceException, UserServiceException, TableServiceException {
        logger.info("adding tables");
        TableService.addTable(
                RestaurantService.getRestaurantByForce("test_restaurant 1"),
                UserService.getManagerByForce("test_manager"),
                1,
                4
        );
        TableService.addTable(
                RestaurantService.getRestaurantByForce("test_restaurant 1"),
                UserService.getManagerByForce("test_manager"),
                2,
                10
        );
        TableService.addTable(
                RestaurantService.getRestaurantByForce("test_restaurant 1"),
                UserService.getManagerByForce("test_manager"),
                3,
                2
        );
        TableService.addTable(
                RestaurantService.getRestaurantByForce("test_restaurant 1"),
                UserService.getManagerByForce("test_manager"),
                4,
                1
        );
        TableService.addTable(
                RestaurantService.getRestaurantByForce("test_restaurant 2"),
                UserService.getManagerByForce("test_manager"),
                1,
                3
        );
        TableService.addTable(
                RestaurantService.getRestaurantByForce("test_restaurant 2"),
                UserService.getManagerByForce("test_manager"),
                2,
                5
        );
        TableService.addTable(
                RestaurantService.getRestaurantByForce("test_restaurant 2"),
                UserService.getManagerByForce("test_manager"),
                3,
                3
        );
        TableService.addTable(
                RestaurantService.getRestaurantByForce("test_restaurant 2"),
                UserService.getManagerByForce("test_manager"),
                100,
                10
        );
    }

    protected void add_reviews() throws UserServiceException, RestaurantServiceException, ReviewServiceException {
        logger.info("adding reviews");
        ReviewService.addOrUpdateReview(
                UserService.getClientByForce("test_client"),
                RestaurantService.getRestaurantByForce("test_restaurant 1"),
                4.5F,
                5F,
                3.5F,
                2.6F,
                "nothing!"
        );

        ReviewService.addOrUpdateReview(
                UserService.getClientByForce("test_client"),
                RestaurantService.getRestaurantByForce("test_restaurant 2"),
                2F,
                2F,
                1.5F,
                1F,
                "BAD!"
        );
    }

    protected void update_reviews() throws UserServiceException, RestaurantServiceException, ReviewServiceException {
        logger.info("updating reviews");
        ReviewService.addOrUpdateReview(
                UserService.getClientByForce("test_client"),
                RestaurantService.getRestaurantByForce("test_restaurant 1"),
                2.5F,
                1F,
                1.5F,
                2.0F,
                "disappointed!"
        );
    }
}
