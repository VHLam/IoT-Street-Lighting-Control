import paho.mqtt.client as mqtt
import time
 
def on_connect(client, userdata, rc):
    print("Connection is established")
    client.subscribe("smartlighting")
 
def on_message(client, userdata, msg):
    text_file = open("log.txt", "a")
    print(msg.payload)
    if msg.payload == '0':
        text_file.write(time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + "Light 1: On" + "\n")
    if msg.payload == '1':
        text_file.write(time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + "Light 1: Off" + "\n")
    if msg.payload == '2':
        text_file.write(time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + "Light 2: On" + "\n")
    if msg.payload == '3':
        text_file.write(time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + "Light 2: Off" + "\n")
    if msg.payload == '4':
        text_file.write(time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + "Light 3: On" + "\n")
    if msg.payload == '5':
        text_file.write(time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + "Light 3: Off" + "\n")
    if msg.payload == '6':
        text_file.write(time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + "Light 4: On" + "\n")
    if msg.payload == '7':
        text_file.write(time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + "Light 4: Off" + "\n")
    print (time.strftime("%d/%m/%Y") + " " + time.strftime("%H:%M:%S") + "  " + str(msg.payload.decode("utf-8")) + "\n")
    text_file.close()
 
client = mqtt.Client()
client.on_connect = on_connect
client.on_message = on_message
 
client.connect("broker.hivemq.com", 1883)
 
client.loop_forever()