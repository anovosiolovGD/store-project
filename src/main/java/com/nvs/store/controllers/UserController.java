
package com.nvs.store.controllers;


import com.nvs.store.exceptions.NotFoundException;
import com.nvs.store.models.UserCustomer;
import org.springframework.web.bind.annotation .*;

import java.sql.SQLException;
import java.util .*;

    @RestController
    @RequestMapping("users")
    public class UserController {

        private int counter = 1;
        private final List<Map<String, String>> users = new ArrayList<Map<String, String>>() {{
            add(new TreeMap<String, String>() {{
                put("id", String.valueOf(counter++));
                put("user", "Mike");
            }});
            add(new TreeMap<String, String>() {{
                put("id", String.valueOf(counter++));
                put("user", "Jack");
            }});
            add(new TreeMap<String, String>() {{
                put("id", String.valueOf(counter++));
                put("user", "Fred");
            }});
        }};

        @GetMapping
        public List<Map<String, String>> allUsers() {
            return users;
        }
        @GetMapping("fromdb")
        public List<UserCustomer> fromDB() throws SQLException {
            return null;

        }
        @GetMapping("{id}")
        public Map<String, String> getUser(@PathVariable String id) {
            return findById(id);
        }

        private Map<String, String> findById(String id) {
            return users.stream()
                    .filter(product -> product.get("id").equals(id))
                    .findFirst()
                    .orElseThrow(NotFoundException::new);
        }

        @PostMapping
        public Map<String, String> addProduct(@RequestBody TreeMap<String, String> user) {
            user.put("id", String.valueOf(counter++));
            users.add(user);
            return user;
        }

        @PutMapping("{id}")
        public Map<String, String> updateProduct(@PathVariable String id, @RequestBody TreeMap<String, String> user) {
            Map<String, String> userFromDB = getUser(id);

            userFromDB.putAll(user);
            userFromDB.put("id", id);
            return userFromDB;
        }

        @DeleteMapping("{id}")
        public void deleteProduct(@PathVariable String id) {
            Map<String, String> user = getUser(id);
            users.remove(user);
        }
    }

