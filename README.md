# filtering_matches
Filter on a prebuilt set of filters

My first assumptions:
1. Load the json file into a Bean which can referenced by dependency injection
2. To explain filter validation based on attributes I have decided to iterate the Matches Java model - here its possible to replace it using JsonPath or lambdas
3. Define the initial use test cases
4. Define the REST controllers and Services
5. We simulate the Matches object like our repository, so we manipulate the data at the service level
6. Create the angular client to consume the API
