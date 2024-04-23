package main.java;

import edu.utulsa.unet.RReceiveUDPI;
import edu.utulsa.unet.UDPSocket;
import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RReceiveUDP implements Closeable, RReceiveUDPI {

  private static final int DEFAULT_TIMEOUT = 10_000;

  /**
   * Returns a byte-buffer representing an ACK for a frame whose sequence number is seq, where
   * seq starts at 0 for the first frame.
   * @param seq the sequence number of the frame
   * @return a byte-buffer for an ACK on sequence number seq
   */
  public static byte[] formatACK(int seq) {
    // TODO: implement formatACK(int)
    throw new UnsupportedOperationException("formatACK(int) not yet implemented");
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
   * Listens for incoming packets and saves the data to the pre-selected file.
   * @return {@code true} if the file is successfully received
   */
  public boolean receiveFile() {
    // TODO: implement receiveFile()
    throw new UnsupportedOperationException("receiveFile() not yet implemented");
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
   * Returns an established socket connection.
   * @return an established DatagramSocket connection
   */
  private DatagramSocket connect() throws IOException {
    return new UDPSocket(this.getLocalPort());
  }

  /**
   * Writes data from the socket connection into the buffer.
   * @param socket an established DatagramSocket connection
   * @param buffer the buffer to write data into
   * @return the packet that was received or {@code null} if an error occurred
   */
  private DatagramPacket receive(DatagramSocket socket, byte[] buffer) {
    try {
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
      socket.setSoTimeout(DEFAULT_TIMEOUT);
      socket.receive(packet);
      return packet;
    } catch (IOException e) {
      // a TIMEOUT is an IOException
      System.out.println(e);
      return null;
    }
  }
}
