import RPi.GPIO as GPIO
import paho.mqtt.publish as publish
import time

GPIO.setmode(GPIO.BCM)

sensorGPIO = 25
GPIO.setup(sensorGPIO, GPIO.IN)

num = 0
bright = 0
dark = 0
while 1 :
    readValue = GPIO.input(sensorGPIO)
    print("Time " + str(num) + ": " + str(readValue))
    num+=1
    
    if readValue == 1:
        bright+=1
    else:
        bright = 0
    if bright == 5:
        publish.single("smartlighting", "8", qos=1, hostname="broker.hivemq.com", client_id="mqtt")
    if readValue == 0:
        dark+=1
    else:
        dark = 0
    if dark == 5:
        publish.single("smartlighting", "9", qos=1, hostname="broker.hivemq.com", client_id="mqtt")
    time.sleep(1)

GPPIO.cleanup()
