![Coverage](.github/badges/jacoco.svg)

# [WIP] Meal Plan Admin 🍜 🗓️
This is a meal plan administration application to help individuals keep track of their hired meal plan.
- 📝 Create n-day based meal plans specifying the number of days and meals per day.
- 📆 Calculates the end date of the plan by using the configured days in which the meals will be delivered (Example: Monday to Friday), the holidays and the "Special days".
- 🍔 "Special days" are dates in which the user asks for a different number of meals. For example: If you hired 2 meals per day but you won't need the dinner this Wednesday because you are going out, you can set a special day for wednesday to set meals = 1.

# Run
```
docker-compose up
```
 
# API documentation
Check http://localhost:8080/swagger-ui/index.html