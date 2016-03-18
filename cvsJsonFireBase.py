import csv
import json
from firebase import firebase

#zonaActual #Nombre de la zona
zonasEncontradas = 0 #Para saber cuantas zonas hay en el archivo
zonaSize = 0 #Tamano de la zona
zonaDisponibles = 0 #Disponibles de la zona
zonaOcupados = 0 #Ocupados en la zona
estacionamiento = 'Estacionamiento1' #Estacionamiento
llaves = [3]


firebase = firebase.FirebaseApplication('https://smartpark1.firebaseio.com', None)

with open('estacionamiento.csv') as csvfile:
    reader = csv.DictReader(csvfile)
    for letra in reader:
        if zonasEncontradas == 0:
            llaves = letra.keys()
            zonaActual = letra[llaves[0]]
            zonaSize += 1
            zonasEncontradas +=1
            if letra[llaves[2]] == '1':
                zonaDisponibles += 1
            else:
                zonaOcupados +=1                
            
        if zonaActual == letra[llaves[0]]:
            zonaSize += 1
            if letra[llaves[2]] == '1':
                zonaDisponibles += 1
            else:
                zonaOcupados +=1               
        else:
            #print("Zona terminada, los datos fueron")
            zonaName = "Zona"+str(zonasEncontradas-1)
            result = firebase.put(estacionamiento,zonaName,{"currentCars":zonaOcupados,"carLimit":zonaSize,"id":(zonasEncontradas-1)})

            #### Debug variables ####
            
            #print("#################################")
            #print("Zona"+str(zonasEncontradas-1))
            #print("Tamano "+str(zonaSize))
            #print("Ocupados: "+str(zonaOcupados))
            #print("Disponiles: "+str(zonaDisponibles))
            #print("Por cierto, llevo "+str(zonaDisponibles)+" zonas")

            #### Debug variables ####
            zonaActual = letra[llaves[0]]
            zonaDisponibles = 0
            zonaOcupados = 0
            if letra[llaves[2]] == '1':
                zonaDisponibles += 1
            else:
                zonaOcupados +=1                   
            zonaSize = 1
            zonasEncontradas += 1 
