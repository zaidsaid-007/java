package main.java;

import edu.utulsa.unet.RSendUDPI;
import edu.utulsa.unet.UDPSocket;
import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class RSendUDP implements Closeable, RSendUDPI {

  /**
   * Returns a byte-buffer representing the ith frame in a sequence of frames to send the input
   * msg. Each frame is assumed to be of size framesize.
   * @param msg the total message being sent
   * @param i the sequence number of this frame
   * @param frameSize the size of each frame
   * @return a byte-buffer for the ith frame in the sequence
   */
  public static byte[] formatPacket(String msg, int i, int frameSize) {
    // TODO: implement formatPacket(String, int, int)
    throw new UnsupportedOperationException("formatPacket(String, int, int) not yet implemented");
  }

  /** Releases any acquired system resources. */
  public void close() throws IOException {}

  /**
   * Returns the name of the file being sent.
   * @return the name of the file being sent
   */
  public String getFilename() {
    // TODO: implement getFilename()
    throw new UnsupportedOperationException("getFilename() not yet implemented");
  }

  /**
   * Returns the port number of the receiver.
   * @return the port number of the receiver
   */
  public int getLocalPort() {
    // TODO: implement getLocalPort()
    throw new UnsupportedOperationException("getLocalPort() not yet implemented");
  }

  /**
   * Returns the selected ARQ algorithm where {@code 0} is stop-and-wait and {@code 1} is
   * sliding-window.
   * @return the selected ARQ algorithm
   */
  public int getMode() {
    // TODO: implement getMode()
    throw new UnsupportedOperationException("getMode() not yet implemented");
  }

  /**
   * Returns the size of the window in bytes when using the sliding-window algorithm.
   * @return the size of the window in bytes for the sliding-window algorithm
   */
  public long getModeParameter() {
    // TODO: implement getModeParameter()
    throw new UnsupportedOperationException("getModeParameter() not yet implemented");
  }

  /**
   * Returns the address (hostname) of the receiver.
   * @return the address (hostname) of the receiver
   */
  public InetSocketAddress getReceiver() {
    // TODO: implement getReceiver()
    throw new UnsupportedOperationException("getReceiver() not yet implemented");
  }

  /**
   * Returns the ARQ timeout in milliseconds.
   * @return the ARQ timeout
   */
  public long getTimeout() {
    // TODO: implement getTimeout()
    throw new UnsupportedOperationException("getTimeout() not yet implemented");
  }

  /**
   * Sends the pre-selected file to the receiver.
   * @return {@code true} if the file is successfully sent
   */
  public boolean sendFile() {
    // TODO: implement sendFile()
    throw new UnsupportedOperationException("sendFile() not yet implemented");
  }

  /**
   * Sets the name of the file being sent.
   * @param fname the name of the file being sent
   */
  public void setFilename(String fname) {
    // TODO: implement setFilename(String)
    throw new UnsupportedOperationException("setFilename(String) not yet implemented");
  }

  /**
   * Sets the port number of the receiver.
   * @param port the port number of the receiver
   * @return {@code true} if the intended port of the receiver is set to the input port
   */
  public boolean setLocalPort(int port) {
    // TODO: implement setLocalPort(int)
    throw new UnsupportedOperationException("setLocalPort(int) not yet implemented");
  }

  /**
   * Sets the selected ARQ algorithm where {@code 0} is stop-and-wait and {@code 1} is
   * sliding-window.
   * @param mode the selected ARQ algorithm
   * @return {@code true} if the ARQ algorithm is set to the input mode
   */
  public boolean setMode(int mode) {
    // TODO: implement setMode(int)
    throw new UnsupportedOperationException("setMode(int) not yet implemented");
  }

  /**
   * Sets the size of the window in bytes when using the sliding-window algorithm.
   * @param n the size of the window in bytes for the sliding-window algorithm
   * @return {@code true} if the window size is set to the input n
   */
  public boolean setModeParameter(long n) {
    // TODO: implemenet setModeParameter(long)
    throw new UnsupportedOperationException("setModeParameter(long) not yet implemented");
  }

  /**
   * Sets the address (hostname) of the receiver.
   * @param receiver the address (hostname) of the receiver
   * @return {@code true} if the intended address of the receiver is set to the input receiver
   */
  public boolean setReceiver(InetSocketAddress receiver) {
    // TODO: implemenet setReceiver(InetSocketAddress)
    throw new UnsupportedOperationException("setReceiver(InetSocketAddress) not yet implemented");
  }

  /**
   * Sets the ARQ timeout in milliseconds.
   * @param timeout the ARQ timeout
   * @return {@code true} if the ARQ timeout is set to the input timeout
   */
  public boolean setTimeout(long timeout) {
    // TODO: implemenet setTimeout(long)
    throw new UnsupportedOperationException("setTimeout(long) not yet implemented");
  }

  /**
   * Returns an established socket connection.
   * @return an established DatagramSocket connection
   */
  private DatagramSocket connect() throws IOException {
    return new UDPSocket(this.getLocalPort());
  }

  /**
   * Sends buffer over socket connection.
   * @param socket an established DatagramSocket connection
   * @param buffer the buffer to send over the socket
   * @return {@code true} if the buffer is successfully sent over the socket
   */
  private boolean send(DatagramSocket socket, byte[] buffer) {
    try {
      DatagramPacket packet =
          new DatagramPacket(
              buffer, buffer.length, this.getReceiver().getAddress(), this.getReceiver().getPort());
      socket.send(packet);
      Thread.sleep(250);
      return true;
    } catch (InterruptedException | IOException e) {
      System.out.println(e);
      return false;
    }
  }
}
