package com.leehaney;

import java.util.HashMap;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine; 

import static spark.Spark.*;

public class App 
{
    public static void main( String[] args )
    {
        staticFileLocation("/public");
        // show something or "get" something. 
        get("/", (req, res) -> {
            HashMap<String, String> model = new HashMap<String, String>();
            // create a key value called "username" and pull in that cookie as its value
            model.put("users_name", req.cookie("users_name"));
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        // create something
        post("/sign-in", (req, res) -> {
            HashMap<String, String> model = new HashMap<>();
            String username = req.queryParams("users_name");
            // set the cookie
            res.cookie("users_name", username);
            model.put("users_name", username);
            return new ModelAndView(model, "sign-in.hbs");
        }, new HandlebarsTemplateEngine());


    }
}
