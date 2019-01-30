#!/usr/bin/python3
import random, string

def randomword(length):
   letters = string.ascii_lowercase
   return ''.join(random.choice(letters) for i in range(length))

#13 cities
cities = ["Zumbrota, 55992", "Pine Island, 55963", "Winona, 55987", "Minneapolis, 55419", "Rochester, 55910", "Wanamingo, 55983", "Denver, 80229", "La Crosse, 54601", "Anchorage, 99503", "Mantorville, 55955"]#, "Kasson, 55944", "Duluth, 55807", "Waco, 76701"]
layoutF = open("LayoutBig.dat", "w")
addressF = open("AddressBig.dat", "w")

streetCount = 1000
for city in cities:
  streets = []
  layoutF.write("Vertical Streets: " + city + "\n")
  for i in range(0, streetCount // 2):
    street = randomword(10)
    streets.append(street)
    layoutF.write(street + "\n")
  layoutF.write("Horizontal Streets: " + city + "\n")
  for i in range(0, streetCount // 2):
    street = randomword(10)
    streets.append(street)
    layoutF.write(street + "\n")
  
  for street in streets:
    addressF.write(str(random.randint(0, 999)) + " " + street + ", " + city + "\n")




