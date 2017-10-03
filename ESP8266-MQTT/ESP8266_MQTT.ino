#include <ESP8266WiFi.h>
#include <PubSubClient.h>
 
// Connect to the WiFi
const char* ssid = "icarus";
const char* password = "112233zz";
const char* mqtt_server = "broker.hivemq.com";

const int output1 = 14;
const int output2 = 12;
const int output3 = 13;
const int output4 = 15;
int light1 = 1;
int light2 = 1;
int light3 = 1;
int light4 = 1;
 
WiFiClient espClient;
PubSubClient client(espClient);
 
void callback(char* topic, byte* payload, unsigned int length) {
   Serial.print("Message arrived [");
   Serial.print(topic);
   Serial.print("] ");
   
   for (int i=0;i<length;i++) {
      char receivedChar = (char)payload[i];
      
      if (receivedChar == '0') {
        digitalWrite(output4, LOW);
        light1 = 0;
      }
      if (receivedChar == '1') {
        digitalWrite(output4, HIGH);
        light1 = 1;
      }
      if (receivedChar == '2') {
        digitalWrite(output3, LOW);
        light2 = 0;
      }
      if (receivedChar == '3') {
        digitalWrite(output3, HIGH);
        light2 = 1;
      }
      if (receivedChar == '4') {
        digitalWrite(output2, LOW);
        light3 = 0;
      }
      if (receivedChar == '5') {
        digitalWrite(output2, HIGH);
        light3 = 1;
      }
      if (receivedChar == '6') {
        digitalWrite(output1, LOW);
        light4 = 0;
      }
      if (receivedChar == '7') {
        digitalWrite(output1, HIGH);
        light4 = 1;
      }
      if (receivedChar == '8') {
        digitalWrite(output4, LOW);
        delay(1000);
        digitalWrite(output3, LOW);
        delay(1000);
        digitalWrite(output2, LOW);
        delay(1000);
        digitalWrite(output1, LOW);
      }
      if (receivedChar == '9'){
        digitalWrite(output4, HIGH);
        delay(1000);
        digitalWrite(output3, HIGH);
        delay(1000);
        digitalWrite(output2, HIGH);
        delay(1000);
        digitalWrite(output1, HIGH);
      }
      if (receivedChar == 'a') {
        if (light1 == 0) {
          client.publish("smartlighting", "0");
        }
        if (light1 == 1) {
          client.publish("smartlighting", "1");
        }
        if (light2 == 0) {
          client.publish("smartlighting", "2");
        }
        if (light2 == 1) {
          client.publish("smartlighting", "3");
        }
        if (light3 == 0) {
          client.publish("smartlighting", "4");
        }
        if (light3 == 1) {
          client.publish("smartlighting", "5");
        }
        if (light4 == 0) {
          client.publish("smartlighting", "6");
        }
        if (light4 == 1) {
          client.publish("smartlighting", "7");
        }
      }
    }
    Serial.println();
}
 
 
void reconnect() {
   while (!client.connected()) {
   Serial.print("Attempting MQTT connection...");
   if (client.connect("ESP8266 Client")) {
    Serial.println("connected");
    client.subscribe("smartlighting");
   } else {
    Serial.print("failed, rc=");
    Serial.print(client.state());
    Serial.println(" try again in 5 seconds");
    delay(5000);
   }
  }
}
 
void setup()
{
  Serial.begin(9600);
  
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  
  pinMode(output1, OUTPUT);
  pinMode(output2, OUTPUT);
  pinMode(output3, OUTPUT);
  pinMode(output4, OUTPUT);

  digitalWrite(output1, HIGH); 
  digitalWrite(output2, HIGH);
  digitalWrite(output3, HIGH);
  digitalWrite(output4, HIGH);
  Serial.println(output1);
 
  client.setServer(mqtt_server, 1883);
  client.setCallback(callback);
 
}
 
void loop()
{
 if (!client.connected()) {
  reconnect();
 }
 client.loop();
}
