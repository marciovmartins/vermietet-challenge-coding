# Architecture & Coding

You have to design and build the system that allows to receive and collect data about energy consumption from different 
villages. As a result, your system should, on demand, give out the consumption report per village for the last 24h. As 
a result of your work, we expect the end-to-end design of the system (a model, system architecture, technology, and 
frameworks choice, testing strategy, etc.). We would also like to see your code for the whole system or reasonable part 
of it. However, if you have a project (at least as complex as this task) on GitHub with a problem statement, then you 
can share it with us as well.

Consider that your system has an API that is called by electricity counters:

`POST /counter_callback`
```
{
    "counter_id": "1",
    "amount": 10000.123
}
```

To get information additional information about the counter you have to call the following external API:

`GET /counter?id=1`
```
{
    "id": "1",
    "village_name": "Villarriba"
}
```

As a result, it's expected that your system will expose the following API:

`GET /consumption_report?duration=24h`
```
{
    "villages": [
        {
            "village_name": "Villarriba",
            "consumption": 12345.123
        },
        {
            "village_name": "Villabajo",
            "consumption": 23456.123
        }
    ]
}    
```

## About the solution

- I did the project in approximately 5-6 hours.
- I only did the "happy" path.
- To keep it simple and skip possible configuration problems I did not use any database framework, as JPA or Hibernate.
- ReportConsumptionRepository and villageRepository repository can be paginated.