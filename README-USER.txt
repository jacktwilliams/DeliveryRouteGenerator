Route Generator Documentation

Features:

Find the exact optimal route between cities using the "Exact Location Mode". This mode uses the Internet to find location coordinates for each city.

Internet-free routing using "Approximate Location Mode". This mode approximates distances between cities using zip codes.

Find the exact optimal route between cities using "Optimal Route". This mode tries every possible route to find the guaranteed best. Works well with less than 10 cities.

For more than 10 cities, select "Use Heuristics". This mode always travels to the next closest city to generate a decent route.

Easy to follow inner-city routing. Inside of a city, we first traverse West to East, then North to South.

User-friendly error-handling. Common errors are alerted to the user and/or recovered from. Examples include switching to approximate location mode if there is internet connectivity issues, switching to heuristics mode if we run out of memory looking exhaustively, and alerting the user to missing files or discrepencies between the Layout and Address files.


Limitations:

Optimal Route Mode can find routes with an approximate maximum of 9 cities.
Can find routes with an approximate maximum of 10,000 addresses per city.

