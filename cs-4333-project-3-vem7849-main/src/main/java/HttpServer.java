package main.java;

import java.io.Closeable;
import java.io.IOException;

/** An implementation of an HTTP server. */
public class HttpServer implements Closeable {

  /**
   * Returns the content type associated with the given filename.
   * @param uri the path identifier for the requested resource
   * @return the content type associated with the given URI,
   *   or {@code null} if the file type is not supported.
   */
  public static String getContentType(String uri) {
    // TODO: implement getContentType(String)
    throw new UnsupportedOperationException("getContentType(String) not yet implemented");
  }

  /**
   * Returns the HTTP server response entity headers.
   * @param serverName the name of the HTTP server
   * @param version the version of the HTTP server
   * @return the HTTP server response entity headers
   */
  public static String[] getEntityHeaders(String serverName, String version) {
    return HttpServer.getEntityHeaders(serverName, version, 0, null);
  }

  /**
   * Returns the HTTP server response entity headers.
   * @param serverName the name of the HTTP server
   * @param version the version of the HTTP server
   * @param length the content length of the requested resource,
   *   or {@code 0} if the resource does not exist.
   * @param contentType the content type of the requested file,
   *   or {@code null} if the content type is unsupported.
   * @return the HTTP server response entity headers
   */
  public static String[] getEntityHeaders(
      String serverName, String version, long length, String contentType) {
    // TODO: implement getEntityHeaders(String, String, long, String)
    throw new UnsupportedOperationException(
        "getEntityHeaders(String, String, long, String) not yet implemented");
  }

  /**
   * Returns the reason phrase associated with the input status code.
   * @param statusCode the status code of an HTTP request
   * @return the reason phrase associated with the input status code
   */
  public static String getReasonPhrase(int statusCode) {
    // TODO: implement getReasonPhrase(int)
    throw new UnsupportedOperationException("getReasonPhrase(int) not yet implemented");
  }

  /**
   * Returns the HTTP server response header.
   * @param serverName the name of the HTTP server
   * @param version the version of the HTTP server
   * @param statusCode the status code of the request
   * @return the HTTP server response header
   */
  public static String getResponseHeader(String serverName, String version, int statusCode) {
    return HttpServer.getResponseHeader(serverName, version, statusCode, null);
  }

  /**
   * Returns the HTTP server response header.
   * @param serverName the name of the HTTP server
   * @param version the version of the HTTP server
   * @param statusCode the status code of the request
   * @param uri the path identifier for the requested resource,
   *   or {@code null} if the URI was not provided
   * @return the HTTP server response header
   */
  public static String getResponseHeader(
      String serverName, String version, int statusCode, String uri) {
    // TODO: implement getResponseHeader(String, String, int, String)
    throw new UnsupportedOperationException(
        "getResponseHeader(String, String, int, String) not yet implemented");
  }

  /**
   * Returns the status code for the input HTTP request. The request must include a HOST field
   * in its header. Valid hosts are 'localhost', '127.0.0.1', and the machine's domain name,
   * and it must be appended with the HTTP server's designated port unless the port is 80.
   * @param domain the domain name of the HTTP server
   * @param port the port of the HTTP server
   * @param request the HTTP request
   * @return the status code for the input HTTP request
   */
  public static int getStatusCode(String domain, int port, String request) {
    // TODO: implement getStatusCode(String, int, String)
    throw new UnsupportedOperationException(
        "getStatusCode(String, int, String) not yet implemented");
  }

  /**
   * Returns the HTTP server response status line.
   * @param statusCode the status code of the request
   * @return the HTTP server response status line
   */
  public static String getStatusLine(int statusCode) {
    // TODO: implement getStatusLine(int)
    throw new UnsupportedOperationException("getStatusLine(int) not yet implemented");
  }

  /**
   * Constructs an HTTP server that listens for HTTP requests
   * on the input port.
   * @param port the port to listen for HTTP requests
   */
  public HttpServer(int port) {
    // TODO: implement HttpServer(int)
    throw new UnsupportedOperationException("HttpServer(int) not yet implemented");
  }

  /** Frees all system resources acquired by this instance. */
  public void close() throws IOException {}

  /**
   * Returns the port acquired by this HTTP server.
   * @return the port acquired by this HTTP server
   */
  public int getPort() {
    // TODO: implement getPort()
    throw new UnsupportedOperationException("getPort() not yet implemented");
  }

  /**
   * Returns the HTTP response for the input HTTP request.
   * @param request the HTTP request being responded to
   * @return the HTTP response for the input HTTP request
   */
  public String getResponse(String request) {
    // TODO: implement getResponse(String)
    throw new UnsupportedOperationException("getResponse(String) not yet implemented");
  }
}
