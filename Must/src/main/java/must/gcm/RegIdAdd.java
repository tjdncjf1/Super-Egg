package must.gcm;

import java.net.URLEncoder;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

public class RegIdAdd {

	private static final String REGISTRATION_ID = "APA91bEmptbpPtLfP64LL3X1rjoguGN-MzKmgDB2LFxbCW6o1E41YLxOPL-v3sT5e3u8ukuykvL2OiNfBqHbAA4tfGtbniPmJ5LUNHiCtftQk5fGWWLYiAg9mn7WTMvw7Ur9MQKMZl7jgkGFsP5BdrSr5FY5qDqYSbJfg74kJ3Y8Sd9bFHHlm9g";
	private static final String APIKEY = "AIzaSyAB2TrQNCtZJXBOSkKc0RyGO9J8LdB4blg"; //GCM에서 발급받은 ApiKey를 입력

	public static void main(String[] args) throws Exception {
		try {
			String sendTlt = "타이틀 제목";
			String sendMsg = "내용 : 메시지가 보입니다";

			Sender sender = new Sender(APIKEY);

			/**
			 * message Build 부분에서 addData로 추가한 값은 어플리케이션의
			 * onMessage(context, intent)에서 Intent로 전달되며
			 * intent.getExtras().getString("title")형태로 얻어와 사용 가능하다.
			 */
			Message message = new Message.Builder()
			.addData("title", URLEncoder.encode(sendTlt, "UTF-8"))
			.addData("msg", URLEncoder.encode(sendMsg, "UTF-8"))
			.build();

			//발송할 메시지, 발송할 타깃(RegistrationId, Retry 횟수)
			Result result = sender.send(message, REGISTRATION_ID, 3);
			if (result.getMessageId() != null) {
				System.out.println("Send success");
			} else {
				String error = result.getErrorCodeName();
				System.out.println("Send fail : " + error);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
