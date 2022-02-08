import com.reference.project.HttpsClient;

public class test {
    public static void main(String[] args){
        HttpsClient client = new HttpsClient();
        client.get("https://google.com");

        System.out.println("Content: " + client.content);
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Response code: " + client.responseCode);
    }
}
