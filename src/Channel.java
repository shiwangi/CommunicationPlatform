/**
 * Abstraction for various channels of communication to customers - email, push, sms etc.
 */
public interface Channel {
    void send(String content, CustomerAddress customerAddress);
}
