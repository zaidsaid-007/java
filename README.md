Overview
For this project we will explore the challenges associated with providing reliable delivery services over an
unreliable network. Three diﬀerent ARQ approaches have been described in class: stop-and-wait,
concurrent logical channels, and sliding window. Your task during this project is to develop a set of Java
classes capable of reliably transferring a ﬁle between two hosts over UDP (TCP already oﬀers reliable
delivery services).
Students will be provided with a UDPSocket class that extends the DatagramSocket class provided by Java
to send a DatagramPacket over UDP. Take time to familiarize yourself with the main two classes provided by
Java for UDP support and their functionality. The UDPSocket class has the additional capability of allowing
programmers to modify the MTU (to model fragmentation), the packet drop rate (to model unreliable
networks) and add random time delays to packets (to model out-of-order reception).
Support material
Parameters regulating message transfers using the UDPSocket class are located in a ﬁle named
unet.properties. This ﬁle may be modiﬁed to test diﬀerent scenarios (the ﬁle includes comments
describing each parameter). Note that you can also use this ﬁle to enable and conﬁgure logging options (to
ﬁle or standard output stream).
Unet.properties
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
# unet properties file
#~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
#== log file name ================================
#
# log.filename = system (standard output stream)
log.filename = system
log.enable = true
#== packet drop rate =============================
#
# n<0 --- Drop random packets (n*-100) probability
# n=0 --- Do not drop any packets
# n=-1 --- Drop all packets
# n>0 --- Drop every nth packet (assuming no delay)
# n=[d,]*d --- Drop select packets e.g. packet.droprate = 4,6,7
packet.droprate = 0
#== packet delay rate ============================
#
# packet delay in milliseconds
# min==max --- in order delivery
packet.delay.minimum = 10
packet.delay.maximum = 100
#== packet maximum transmission size =============
1/8README.md
2024-02-28
#
# -1 unlimited (Integer.MAX_VALUE)
packet.mtu = 1500
A brief tutorial on how to use UDP in Java is provided in the Appendix. A supporting jar ﬁle (unet.jar) as well
as the default unet.properties ﬁle are provided in this repo.
Use TUGrader.java to ensure that all unit tests in the provided test ﬁle(s) pass.
Assignment
Programs
A minimum of two classes will have to be submitted, RSendUDP.java and RReceiveUDP.java (do not make
them part of the edu.utulsa.unet package) where each class must implement the
edu.utulsa.unet.RSendUDPI and edu.utulsa.unet.RReceiveUDPI interfaces respectively (as speciﬁed
below, provided in unet.jar).
package edu.utulsa.unet;
import java.net.InetSocketAddress;
public interface RSendUDPI {
public String getFilename();
public int getLocalPort();
public int getMode();
public long getModeParameter();
public InetSocketAddress getReceiver();
public long getTimeout();
public boolean sendFile();
public void setFilename(String fname);
public boolean setLocalPort(int port);
public boolean setMode(int mode);
public boolean setModeParameter(long n);
public boolean setReceiver(InetSocketAddress receiver);
public boolean setTimeout(long timeout);
}
2/8README.md
2024-02-28
package edu.utulsa.unet;
public interface RReceiveUDPI {
public String getFilename();
public int getLocalPort();
public int getMode();
public long getModeParameter();
public boolean receiveFile();
public void setFilename(String fname);
public boolean setLocalPort(int port);
public boolean setMode(int mode);
public boolean setModeParameter(long n);
}
The setMode method speciﬁes the algorithm used for reliable delivery where mode is 0 or 1 (to specify
stop-and-wait and sliding window respectively). If the method is not called, the mode should default to
stop-and-wait. Its companion, getMode simply returns an int indicating the mode of operation.
The setModeParameter method is used to indicate the size of the window in bytes for the sliding window
mode. A call to this method when using stop-and-wait should have no eﬀect. The default value should be
256 for the sliding window algorithm. Hint: your program will have to use this value and the MTU (max
payload size) value to calculate the maximum number of outstanding frames you can send if using the
Sliding Window algorithm. For instance, if the window size is 2400 and the MTU is 20 you could have up to
120 outstanding frames on the network.
The setFilename method is used to indicate the name of the ﬁle that should be sent (when used by the
sender) or the name to give to a received ﬁle (when used by the receiver).
The setTimeout method speciﬁes the timeout value in milliseconds. Its default value should be one second.
A sender uses setReceiver to specify IP address (or fully qualiﬁed name) of the receiver and the remote port
number. Similarly, setLocalPort is used to indicate the local port number used by the host. The sender will
send data to the speciﬁed IP and port number. The default local port number is 12987 and if an IP address
is not speciﬁed then the localhost is used.
The methods sendFile and receiveFile initiate ﬁle transmission and reception respectively. Methods
returning a boolean should return true if the operation succeeded and false otherwise.
Operation Requirements
RReceiveUDP
3/8README.md
2024-02-28
Should print an initial message indicating the local IP, the ARQ algorithm (indicating the value of n if
appropriate) in use and the UDP port where the connection is expected.
Upon successful initial connection from a sender, a line should be printed indicating IP address and
port used by the sender.
For each received message print its sequence number and number of data bytes
For each ACK sent to the sender, print a message indicating which sequence number is being
acknowledged
Upon successful ﬁle reception, print a line indicating how many messages/bytes were received and
how long it took.
RSendUDP
Should print an initial message indicating the local IP, the ARQ algorithm (indicating the value of n if
appropriate) in use and the local source UDP port used by the sender.
Upon successful initial connection, a line should be printed indicating address and port used by the
receiver.
For each sent message print the message sequence number and number of data bytes in the
message
For each received ACK print a message indicating which sequence number is being acknowledged
If a timeout occurs, i.e. an ACK has been delayed or lost, and a message needs to be resent, print a
message indicating this condition
Upon successful ﬁle transmission, print a line indicating how many bytes were sent and how long it
took.
