[![logo](https://www.gnu.org/graphics/gplv3-127x51.png)](https://choosealicense.com/licenses/gpl-3.0/)
# GUIÓN DE PRÁCTICAS[^nota1]
## Sesión 11: Java Message Service (I)

En este último módulo de prácticas trabajaremos con el problema de comunicación entre procesos que no comparten memoria. Al no compartir memoria, los procesos, no podrán compartir variables para el intercambio de información. Para ello utilizaremos como herramienta el paso de mensajes. 

## 11.1 Paso de mensajes

El paso de mensajes es un método de comunicación entre componentes software o aplicaciones. Un sistema de mensajes es un servicio peer-to-peer: un cliente de mensajería puede enviar mensajes a, o recibir mensajes de, otro cliente. Basta con que ambos se conecten a agentes de mensajería (MOMs) que proporcionen servicios de creación, envío, recepción y lectura de mensajes. Los mensajes permiten una comunicación distribuida débilmente acoplada: un componente envía un mensaje a un destino y el receptor lo recoge del mismo.

Sin embargo, ni el emisor ni el receptor tienen que estar disponibles al mismo tiempo para comunicarse. De hecho, el emisor no tiene porqué saber nada del receptor, y viceversa. Ambos sólo deben saber el formato del mensaje y el destino del mensaje. Esto hace que la mensajería difiera de tecnologías fuertemente acopladas, tales como [RMI](https://es.wikipedia.org/wiki/Java_Remote_Method_Invocation), que requieren que la aplicación cliente conozca los métodos de la aplicación remota.

Una aplicación utilizará el paso de mensajes cuando queramos que:

- ciertos componentes no dependan de la interface de otros componentes, de modo que éstos puedan sustituirse fácilmente.
- la aplicación funcione independientemente de si todos los componentes se están ejecutando de forma simultánea.
- el modelo de negocio de la aplicación permita a un componente enviar información a otro componente y que continúe su procesamiento sin esperar a una respuesta inmediata.

Para establecer el servicio de paso de mensajes se utilizará un [Middleware Orientado a Mensajes](https://en.wikipedia.org/wiki/Message-oriented_middleware) (MOM).

## 11.2 Middleware Orientado a Mensajes (MOM)

El **M**idleware **O**rientado a **M**ensajes (MOM) es un categoría de software para la intercomunicación de sistemas que ofrece una manera segura, escalable, confiable y con bajo acoplamiento. Los MOMs permiten la comunicación entre aplicaciones mediante un conjunto de APIs ofrecidas por cada proveedor y lenguaje, así pues, tendremos un API propietaria y diferente por cada MOM existente.

La idea principal de un MOM es que actúa como un mediador entre los emisores y los receptores de mensajes. Esta mediación ofrece un nuevo nivel de desacoplamiento en la mensajería empresarial. Así pues, un MOM se utiliza para mediar en la conectividad y la mensajería, no solo entre las aplicaciones y el *mainframe*, sino de una aplicación a otra.

![][imagen1]

A un nivel más alto, los mensajes son unidades de información de negocio que se envían de una aplicación a otra a través de un MOM. Estos mensajes se envían y reciben por aquellos clientes que se conectan o subscriben a los mensajes. Este mecanismo es el que permite el acoplamiento débil entre emisores y receptores, ya que no se requiere que ambos estén conectados simultáneamente al MOM para enviar y/o recibir los mensajes. Esto es la mensajería asíncrona.

Los MOMs añadieron muchas características a la mensajería empresarial que previamente no eran posibles cuando los sistemas estaban fuertemente acoplados, tales como la persistencia de los mensajes, enrutamientos complejos de mensajes, transformación de los mensajes, etc... La persistencia de mensajes ayuda a mitigar las conexiones lentas o poco fiables realizadas por lo emisores y receptores o en una situación donde el fallo de un receptor no afecta al estado del emisor. El enrutamiento complejo de mensajes genera una cantidad de posibilidades que incluyen la entrega de un único mensaje a muchos receptores, enrutamiento de mensajes basados en propiedades del contenido del mensaje, etc... La transformación de mensajes permite la comunicación entre dos aplicaciones que no trabajan con el mismo formato de mensajes.

Además, la mayoría de MOMs existentes en el mercado ofrecen soporte para diversos protocolos de conectividad, como pueden ser HTTP/S, SSL, TCP/IP, UDP, etc... Incluso algunos proveedores ofrecen soporte para múltiples lenguajes de programación, facilitando el uso de MOMs en una amplia variedad de entornos. Este gran abanico de protocolos, lenguajes y APIs provoca la aparición de JMS para estandarizar la mensajería dentro del mundo Java.

## 11.3 Java Message Service (JMS)

JMS se separa de las APIs propietarias de cada proveedor para ofrece un API estándar (mediante un conjunto de interfaces) para la mensajería empresarial, de modo que mediante Java podamos enviar y recibir mensajes sin atarnos a ningún proveedor. JMS además minimiza el conocimiento de mensajería empresarial que debe tener un programador Java para desarrollar complejas aplicaciones de mensajería, mientras mantiene la portabilidad entre las diferentes implementaciones de proveedores JMS.

Cuidado, JMS no es un MOM. Se trata de una especificación que abstrae la interacción entre los clientes de mensajería y los MOMs del mismo modo que JDBC abstrae la comunicación con las BBDD relacionales. El siguiente gráfico muestra como JMS ofrece un API que utilizan los clientes de mensajería para interactuar con MOMs específicos via proveedores JMS que manejan la interacción con el MOM específico. De este modo, JMS reduce la barrera para la creación de aplicaciones de mensajería, facilitando la portabilidad a otros proveedores JMS.

![][imagen2]

JMS permite que la comunicación entre componentes sea **débilmente acoplada**, **asíncrona** (el proveedor JMS entrega los mensajes al destino conforme llegan, y el cliente no tiene que solicitar los mensajes para recibirlos) y **fiable** (JMS asegura que cada mensaje se entrega una y solo una vez, y mediante inferiores niveles de fiabilidad permite la pérdida o el duplicado de mensajes en aquellas aplicaciones que requieran menos control).

Originalmente creada por Sun conjuntamente con un grupo de compañías de la industria de la mensajería empresarial, la primera versión de la especificación JMS data de 1998. La última revisión fue en 2002 con mejoras necesarias y desde entonces se trata de una tecnología estable y madura. La revisión JMS 1.1 unificó los dos conjuntos de APIs para trabajar con los dos dominios de mensajería, de modo que ahora sólo necesitamos una API para trabajar con ambos dominios.

## 11.4 ActiveMQ

[Apache ActiveMQ](https://activemq.apache.org/) es un MOM (broker) de código abierto escrito en Java . Implementa un cliente completo del estándar Java Message Service (JMS).
  
Los clientes en diferentes plataforma incluyen Java, así como varios otros clientes Python, Ruby, etc... La comunicación se gestiona mediante TCP y tiene la capacidad de usar cualquier base de datos como proveedor de persistencia JMS, además de la memoria virtual, caché y persistencia.  
  
El proyecto *ActiveMQ* fue creado originalmente por sus fundadores de LogicBlaze en 2004, como un agente de mensajes de código abierto. El código y la marca comercial *ActiveMQ* fueron donados a Apache Software Foundation en 2007, donde los fundadores continuaron desarrollando el código base con la comunidad Apache extendida.

Entre sus características más relevantes encontramos:

- Es Open Source. Se distribuye bajo la Licencia Apache.
- Actúa como mediador de mensajes entre aplicaciones de envío y recepción.
- Proporciona comunicación asíncrona entre aplicaciones.
- Implementa la especificación JMS (Java Message Service), y proporciona una gran cantidad de API para diferentes lenguajes como PHP, C / C ++, .Net, Ruby, etc. Entre los componentes de nuestro sistema (uno de los pilares de SOA).
- Soporta diferentes protocolos de conexión como HTTP, TCP, SSL, etc …
	- El más usado es el transporte que TCP permite a los clientes conectarse a un agente *ActiveMQ* remoto mediante un socket TCP.
- Interfaz de administración gráfica.

### 11.4.1 Instalación de ActiveMQ

Como se ha presentado anteriormente necesitamos un MOM para poder realizar el intercambio de mensajes entre los procesos implicados en un problemas. Para ello debemos:

- o realizar una instalación local del MOM.
- o utilizar un MOM ya instalado en un servidor conocido.

En esta sección se presentan los pasos necesarios para la instalación local del MOM *ActiveMQ* para la realización de las prácticas. Un requisito adicional es tener instalada una versión de Java 1.7 o superior. Como para el desarrollo de las prácticas ya hemos instalado la versión Java 1.8 se cumple este primer requisito.

1. **Descargar ActiveMQ**:
Dependiendo del sistema operativo que se tiene instalado hay que descargar el archivo de [ActiveMQ](https://activemq.apache.org/components/classic/download/)  y almacenarlo en un directorio conocido en nuestro ordenador.

2. **Descomprimir el archivo**:
Se descomprime el archivo descargado en el directorio local de nuestro ordenador.

3. **Ejecución de ActiveMQ**:
Se abre una ventana de órdenes del sistema y cambiamos al directorio donde se ha descomprimido el archivo que hemos descargado:
	- Si el sistema es Windows: `bin\activemq.bat start`
	- Si el sistema es Linux,MacOS: `./bin/activemq start`

4. **Comprobamos si está activo**:
Abrimos un navegador web y ponemos la dirección [`http://localhost:8161`](http://localhost:8161). 

![][imagen3]

En la imagen se observa la interface gráfica que nos permite administrar *ActiveMQ* con **usuario**: `admin` y **password**: `admin`. La **URL** de conexión será `tcp://localhost:61616`.

5. **Parar la ejecución**:
Cuando ya no se necesite el servicio podemos pararlo:
	- Si el sistema es Windows: `bin\activemq.bat stop`
	- Si el sistema es Linux,MacOS: `./bin/activemq stop`

### 11.4.2 Servidor remoto ActiveMQ

Para la realización de las prácticas tengo un servidor instalado con el MOM *ActiveMQ* y la **URL** de conexión es: `tcp://suleiman.ujaen.es:8018`.

## 11.4 Arquitectura JMS

Para estandarizar el API, JMS define de un modo formal muchos conceptos y elementos del mundo de la mensajería:

- **Cliente JMS**: Una aplicación 100% Java que envía y recibe mensajes. Cualquier componente JavaEE puede actuar como un cliente JMS.
	- **Clientes No-JMS**: una aplicación escrita en un lenguaje que no es Java que envía y recibe mensajes.
	- **Productor JMS**: una aplicación cliente que crea y envía mensajes JMS.
	- **Consumidor JMS**: una aplicación cliente que recibe y procesa mensajes JMS.
- **Proveedor JMS**: implementación de los interfaces JMS el cual está idealmente escrito 100% en Java. El proveedor debe ofrecer prestaciones tanto de administración como de control de los recursos JMS. Toda implementación de la plataforma Java incluye un proveedor JMS.
- **Mensaje JMS**: elemento principal de JMS; objeto (cabecera + propiedades + cuerpo) que contiene la información y que es enviado y recibido por clientes JMS.
- **Dominio JMS**: los dos estilos de mensajería: PTP y Pub/Sub.
- **Objetos Administrados**: objetos JMS preconfigurados que contienen datos de configuración específicos del proveedor, los cuales utilizarán los clientes. Los clientes acceden a estos objetos mediante JNDI.
	- **Factoría de Conexión**: los clientes utilizan una factoría para crear conexiones al proveedor JMS.
	- **Destino**: objeto (cola/tópico) al cual se direccionan y envían los mensajes, y desde donde se reciben los mensajes.

## 11.5 Modelo de programación JMS

En la siguiente imagen se presenta el modelo en el que se basa la programación JMS:

![][imagen4]

A continuación se detallan los diferentes componentes que se presentan en la imagen.

### 11.5.1 Factorás de Conexión

La factoría de conexión es el objeto que utiliza el cliente para crear una conexión con el proveedor (ActiveMQ en nuestro caso), encapsulando un conjunto de parámetros de configuración de la conexión que han sido previamente definidos por el administrador del servidor de mensajes. Cada factoría de conexión es una instancia de [`ConnectionFactory`](https://docs.oracle.com/javaee/7/api/javax/jms/ConnectionFactory.html), en nuestro caso utilizaremos [`ActiveMQConnectionFactory`](https://activemq.apache.org/maven/apidocs/org/apache/activemq/ActiveMQConnectionFactory.html "class in org.apache.activemq") para establecer la conexión con el MOM *ActiveMQ*.

```java
...
ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
...
```

El valor de la constante `BROKER_URL` identifica la dirección donde se encuentra nuestro proveedor:

1. Si se encuentra en el mismo ordenador que nuestra aplicación: `ActiveMQConnection.DEFAULT_BROKER_URL`
2. Para las prueban con servidor remoto utilizaremos el valor: `"tcp://suleiman.ujaen.es:8018"`

### 11.5.2 Conexiones

De esta forma se encapsula una conexión virtual con el proveedor JMS, y puede representar un *socket* TCP/IP entre el cliente y el servidor que representa al proveedor. Al crear una conexión, se crean objetos, tanto en la parte del cliente como en la del servidor, que gestionan el intercambio de mensajes entre el cliente y el sistema de mensajes. Mediante una conexión crearemos una o más sesiones en las que se producen y se consumen mensajes. Las conexiones implementan la interface [`Connection`](https://docs.oracle.com/javaee/7/api/javax/jms/Connection.html "interface in javax.jms"). A partir de una `ConnectionFactory`, podemos crear una conexión del siguiente modo:

```java
...
Connection connection = connectionFactory.createConnection();
...
```

Al finalizar la aplicación, tenemos que cerrar toda conexión. Es muy importante cerrar las conexiones porque sino podemos provocar la sobrecarga del proveedor JMS. Al cerrar una conexión también cerramos sus sesiones y sus productores y consumidores de mensajes.

```java
...
connection.close();
...
```

Antes de que nuestras aplicaciones puedan consumir mensajes, debemos llamar al método `start()` de la conexión. Si queremos parar el envío de mensajes de forma temporal sin cerrar la conexión, podemos utilizar el método `stop()`.

### 11.5.3 Sesiones

Una sesión ([`Session`](https://docs.oracle.com/javaee/7/api/javax/jms/Session.html "interface in javax.jms")) es un *contexto monohilo* para producir y consumir mensajes. Mediante las sesiones crearemos:

-   Productores de mensajes.
-   Consumidores de mensajes.
-   Mensajes.
-   Navegadores de colas (Queue Browser).
-   Colas y tópicos temporales.
    
Existen dos tipos de sesiones: las **transaccionales** y las **no-transaccionales**. Las **transnacionales** se caracterizan porque todos los mensajes enviados y recibidos se tratan como una unidad atómica que está sujeta al protocolo **commit/rollback** (confirmar o deshacer). En estas sesiones no es necesario realizar acuses de recibo o **acknowledgements**. En las **no-transaccionales** hay que seleccionar un tipo de acuse de recibo. En este caso, el tipo `Session.AUTO_ACKNOWLEDGE` indica que la sesión acusa el recibo de un mensaje una vez que la aplicación receptora lo ha procesado. Para nuestros ejemplos utilizaremos sesiones **no-transaccionales**:

```java
...
Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
...
```

El primer parámetro indica que la sesión no es transaccional, y el segundo que la sesión confirmará la recepción exitosa de los mensajes de forma automática mediante un acuse de recibo.

Para crear una sesión **transaccional**, utilizaremos el siguiente fragmento:

```java
...
Session session = connection.createSession(true, 0);
...
```

En este caso, hemos creado una sesión transaccional pero que no especifica la confirmación de los mensajes. Revisar la documentación para profundizar en otros en el uso de las sesiones.

### 11.5.4 Mensajes

Los mensajes también se crean a partir de objetos de sesión obteniendo un objeto que implementa la interface [`TextMessage`](https://docs.oracle.com/javaee/7/api/javax/jms/TextMessage.html "interface in javax.jms"). Por ejemplo, para crear un mensaje de tipo texto:

```java
...
TextMessage message = session.createTextMessage();
...
```

Los métodos de la interface son:

- `String getText()` : Obtenemos el objeto `String` que representa el contenido del mensaje.
- `void setText(String string)`: Asignamos el objeto `string` como contenido del mensaje.

Los mensajes encapsulan información a intercambiar entre aplicaciones. Un mensaje contiene tres componentes: los campos de la cabecera, las propiedades específicas de la aplicación y el contenido mensaje. 


### 11.5.4 Productor (Emisor)

Un productor (emisor) de mensajes es un objeto creado por una sesión y que se utiliza para enviar mensajes a un destino. Implementa el interface [`MessageProducer`](https://docs.oracle.com/javaee/7/api/javax/jms/MessageProducer.html). A partir de la sesión y un destino:

```java
...
MessageProducer producer = session.createProducer(destination);
...
```

Una vez creado un productor y el mensaje utilizaremos el método send para completar el envío al destino:

```java
...
TextMessage message = session.createTextMessage("Job number: " + i);
producer.send(message);
...
```

Se puede crear un productor sin identificar mediante un `null` como parámetro en el método `createProducer(.)`. Mediante este tipo de productores, el destino no se especifica hasta que se envía un mensaje, especificándolo como primer parámetro.

```java
...
MessageProducer anonProd = session.createProducer(null);
anonProd.send(dest, message);
...
```

El productor estará activo hasta que se ejecute el método `close()`. Es conveniente cerrar el productor una vez ha completado el envío de los mensajes.

### 11.5.5 Consumidor (Receptor)

Un consumidor (receptor) de mensajes es un objeto creado por una sesión y que se utiliza para recibir mensajes enviados desde un destino. Implementa el interfaz [`MessageConsumer`](https://docs.oracle.com/javaee/7/api/javax/jms/MessageConsumer.html "interface in javax.jms"). A partir de la sesión y un destino:

```java
...
MessageConsumer consumer = session.createConsumer(destination);
...
```

Un consumidor de mensajes permite a un cliente JMS registrar su interés en un destino con un proveedor JMS. El proveedor gestiona la entrega de mensajes desde un destino a los consumidores registrados en dicho destino.

Para consumir un mensaje disponemos de dos opciones:

#### De forma síncrona

Para consumir un mensaje de forma **síncrona** utilizaremos el método `receive()`.

```java
...
TextMessage msg = (TextMessage) consumer.receive();
...
```

#### De forma asíncrona

Para consumir un mensaje de forma asíncrona necesitamos un **listener** de mensajes que es un objeto que implementa la interface [`MessageListener`](https://docs.oracle.com/javaee/7/api/javax/jms/MessageListener.html "interface in javax.jms") . En siguiente guión se explicará en más detalle la forma en que podemos definir una clase que implemente esta interface.

Para recibir mensajes de esta forma:

```java
...
consumer.setMessageListener(new TextMsgListener("AsycConsumer"));
...
```

La clase `TextMsgListener` implementa la interface `MessageListener`. Como la recepción de los mensajes es asíncrona el programador deberá sincronizar adecuadamente la ejecución en el caso de que no haya un mensaje disponible.


### 11.5.6 Destinos

Un destino ([`Destination`](https://docs.oracle.com/javaee/7/api/javax/jms/Destination.html)) es el objeto que utiliza el cliente para especificar el destino de los mensajes que **produce** el emisor y el origen de los mensajes que **consume** el receptor. En **PTP (Peer to Peer)** los destinos son las colas ([`Queue`](https://docs.oracle.com/javaee/7/api/javax/jms/Queue.html "interface in javax.jms")), mientras que en **Pub/Sub (Public/Subscription)** son los tópicos ([`Topic`](https://docs.oracle.com/javaee/7/api/javax/jms/Topic.html "interface in javax.jms")). Una aplicación JMS puede utilizar múltiples colas o tópicos (o ambos). 

- En el caso de un destino para **P2P**:

```java
...
Destination destination = session.createQueue(QUEUE);
...
```

- En el caso de un destino para **Pub/Sub**:

```java
...
Destination destination = session.createTopic(TOPIC);
...
```

En el siguiente guión se dará una explicación más extensa sobre las posibilidades que tenemos a nuestra disposición según el destino que tengamos seleccionado para nuestros mensajes.

### 11.5.7 Ejemplo de prueba

Se presenta un primer ejemplo para demostrar el funcionamiento de envío de mensajes JMS entre procesos productores y procesos consumidores. En el ejemplo se muestran dos aplicaciones:

- `GuionSesion11Sync` conexión **P2P** con recepción síncrona de los mensajes.
- `GuionSesion11Async` conexión **Pub/Sub** con recepción síncrona de los mensajes.

#### Configuración del proyecto maven

Para poder utilizar la biblioteca [ActiveMQ](https://activemq.apache.org/maven/apidocs/) en las prácticas de la asignatura debemos modificar el fichero `pom.xml` y añadir la dependencia de la biblioteca:

```xml
<dependencies>
    <dependency>
        <groupId>org.apache.activemq</groupId>
        <artifactId>activemq-all</artifactId>
        <version>5.15.12</version>
    </dependency>
</dependencies>
```

#### Constantes

En la interface `Constantes` están definidas las constantes que se utilizarán en los ejemplos:

```java
...
public static final String QUEUE = "uja.ssccdd.connection";
public static final String BROKER_URL = "tcp://suleiman.ujaen.es:8018";
...
```

Estas dos constantes definen el destino para la conexión **P2P** y la localización del proveedor JMS.

#### GuionSesion11Sync

En el ejemplo se crean un número de tareas `PRODUCER` y cuya ejecución envía al destino una serie de de mensajes de texto, `NUM_MSG`, que serán consumidos por un número de tareas `CONSUMER` de forma síncrona. Para la ejecución de las tareas se utiliza un marco [`Executor`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html "interface in java.util.concurrent").

1. Definimos la clase `Producer` que implementa la interface `Runnable`. 
2. Implementamos el método `run()` donde crean los elementos necesarios para el envío de los mensajes al proveedor, se realiza el envío de los mensajes al destino y finaliza la ejecución liberando los recursos asociados a la tarea

```java
@Override
public void run() {
    System.out.println("HILO-" + getiD() + " Starting example Q Producer now...");
        
    try {
        before();
        execution();
    } catch (Exception e) {
        System.out.println("HILO-" + getiD() + 
                           " Caught an exception during the example: " + e.getMessage());
    } finally {
        after();
        System.out.println("HILO-" + getiD() + " Finished running the sample Q Producer");
    }
}
```

3. En método `before()` se establece la conexión con el proveedor JMS y se inicia, se crea una sesión para el productor y se establece el destino como una conexión **P2P**. 

```java
public void before() throws Exception {
    connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
    connection = connectionFactory.createConnection();
    connection.start();
    session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    destination = session.createQueue(QUEUE);
}
```

4. El método `execution()` incluye las sentencias de código necesarias para la creación del productor que nos permitirá el envío de los mensajes al destino. Debemos finalizar el productor antes de finalizar el método.

```java
public void execution() throws Exception {
    MessageProducer producer = session.createProducer(destination);

    for (int i = 0; i < NUM_MSG; ++i) {
        TextMessage message = session.createTextMessage("Job number: " + i);
        producer.send(message);
        System.out.println("Producer sent Job("+i+")");
    }

    producer.close();
}
```

5. Para finalizar el método `after()` cierra la conexión con el proveedor JMS.

```java
public void after() {
    try {
        if (connection != null) {
            connection.close();
        }
    } catch (Exception ex) {
        // No hacer nada
    }    
}
```

6. Definimos la clase `SyncConsumer` que implementa la interface `Runnable`. 
7. Implementamos el método `run()` con una estructura similar al de la clase `Producer`. 
8. El método `execution()` incluye las sentencias de código necesarias para la creación del consumidor que nos permitirá la recepción de los mensajes del destino de forma síncona, es decir, el consumidor espera hasta que el mensaje esté disponible. Debemos finalizar el consumidor antes de finalizar el método.

```java
public void execution() throws Exception {
    MessageConsumer consumer = session.createConsumer(destination);
        
    for (int i = 0; i < NUM_MSG; ++i) {
        TextMessage msg = (TextMessage) consumer.receive();
        System.out.println("Worker processing job: " + msg.getText());
    }
        
    connection.stop();
    consumer.close();
}
```

9. Para finalizar implementamos el método `main()` del ejemplo. Revisar el código de ejemplo.

 
#### GuionSesion11Async

En el ejemplo se crean un número de tareas `PRODUCER` y cuya ejecución envía al destino una serie de de mensajes de texto, `NUM_MSG`, que serán consumidos por un número de tareas `CONSUMER` de forma asíncrona. Para la ejecución de las tareas se utiliza un marco [`Executor`](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executor.html "interface in java.util.concurrent").

1. Se utiliza la misma definición de la clase `Producer` para esta aplicación para el envío de los mensajes al destino.
2. Definimos la clase `AsyncConsumer` que implementa la interface `Runnable` de forma similar a la clase `SyncConsumer`.
3. Las diferencias entre ambas clases solo se encuentra en el método `execution()` donde se incluyen las sentencias de código necesarias para la recepción de mensajes del destino de forma asíncrona. Como medida de sincronización, si no hay mensajes disponibles, es la espera por un tiempo antes de finalizar el método.

```java
public void execution() throws Exception {
    MessageConsumer consumer = session.createConsumer(destination);
        
    consumer.setMessageListener(new TextMsgListener("AsycConsumer"));
        
    // Espera antes de finalizar
    TimeUnit.MINUTES.sleep(TIME_SLEEP);
        
    connection.stop();
    consumer.close();
}
```

4. De define la clase `TextMsgListener` que implementa la interface `MessageListener`. Esta clase permite la recepción asíncrona de los mensajes del destino. Para ello se debe implementar el método `onMessage(Message message)`.

```java
public void onMessage(Message message) {
	try {			
        if (message instanceof TextMessage) {		
            TextMessage textMessage = (TextMessage) message;
		    System.out.println(consumerName + " received " + textMessage.getText());
        } else
            System.out.println("Unknown message");
	} catch (JMSException e) {			
        e.printStackTrace();
	}	
}
```

5. La implementación del método main() de la aplicación es similar al del ejemplo anterior. Revisar el código del ejemplo..`


[imagen1]:https://gitlab.com/ssccdd/guionsesion11/-/raw/master/img/imagen1.png
[imagen2]:https://gitlab.com/ssccdd/guionsesion11/-/raw/master/img/imagen2.png
[imagen3]:https://gitlab.com/ssccdd/guionsesion11/-/raw/master/img/imagen3.png
[imagen4]:https://gitlab.com/ssccdd/guionsesion11/-/raw/master/img/imagen4.png

---
[^nota1]: Para una documentación más extensa consultar *ActiveMQ in Action* y *Java Message Service, 2nd Edition* que se encuentra disponible para los alumnos de la [Universidad de Jaén](https://www.ujaen.es/) por medio de su servicio de [Biblioteca Digital](http://www.ujaen.debiblio.com/login?url=https://learning.oreilly.com/home/).
<!--stackedit_data:
eyJoaXN0b3J5IjpbODg1OTkyMjYsLTUyNDE4Njk4MF19
-->