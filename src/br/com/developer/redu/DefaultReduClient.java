package br.com.developer.redu;

import br.com.developer.redu.models.*;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;


/**
 * @author igor
 * Classe que faz uma implementação concreta do Wrapper. Herda do ReduClient que fez 
 * a implementação das requisições. Objetivo dessa classe é servir como um Factory dos modelos.
 * Aqui são definidos os  parametros do ReduClient  e o seus respectivos tipos no initTypes.
 */
public class DefaultReduClient extends ReduClient<Course, Enrollment, Environment, Space, Subject,
        User, Status, ChatMessage, Chat, Progress, Lecture> {

    public DefaultReduClient(String consumerKey, String consumerSecret) {
        super(consumerKey, consumerSecret);
    }
    public DefaultReduClient(String consumerKey, String consumerSecret, String pin) throws MalformedURLException, IOException{
        super(consumerKey, consumerSecret, pin);
    }
    
    @Override
    protected void initTypes() {
        super.userClass = User.class;
        super.userList = new TypeToken<List<User>>(){}.getType();
        super.subjectClass = Subject.class;
        super.subjectList = new TypeToken<List<Subject>>(){}.getType();
        super.enrollmentClass = Enrollment.class;
        super.enrollmentList = new TypeToken<List<Enrollment>>(){}.getType();
        super.courseClass = Course.class;
        super.courseList = new TypeToken<List<Course>>(){}.getType();
        super.spaceClass = Space.class;
        super.spaceList = new TypeToken<List<Space>>(){}.getType();
        super.statusList = new TypeToken<List<Status>>() {}.getType();
        super.statusClass = Status.class;
        super.environmentClass = Environment.class;
        super.chatMessageClass = ChatMessage.class;
        super.chatMessageList= new TypeToken<List<ChatMessage>>(){}.getType();
        super.chatClass = Chat.class;
        super.chatList = new TypeToken<List<Chat>>(){}.getType();
        super.progressClass = Progress.class;
        super.progressList = new TypeToken<List<Progress>>(){}.getType();
        super.lectureClass = Lecture.class;
        super.lectureList = new TypeToken<List<Lecture>>(){}.getType();
    }
}
