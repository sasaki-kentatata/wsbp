package com.example.wsbp.service;

import com.example.wsbp.data.*;
import com.example.wsbp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final BeanNameUrlHandlerMapping beanNameUrlHandlerMapping;
    private IAuthUserRepository authUserRepos;
    private ISubjectRepository subjectRepos;
    private ILectureRepository lectureRepos;
    private ILecture_detailRepository lecture_detailRepos;
    private ILecture_passwordRepository lecture_passwordRepos;
    private ILecture_attendanceRepository lecture_attendanceRepos;
    private IChatRepository chatRepos;

    @Autowired
    public UserService(IAuthUserRepository authUserRepos, ISubjectRepository subjectRepos, ILectureRepository lectureRepos,
                       ILecture_detailRepository lecture_detailRepos,Lecture_passwordRepository lecture_passwordRepos,
                       ILecture_attendanceRepository lecture_attendanceRepos, BeanNameUrlHandlerMapping beanNameUrlHandlerMapping,
                       IChatRepository chatRepos) {
        this.authUserRepos = authUserRepos;
        this.subjectRepos = subjectRepos;
        this.lectureRepos = lectureRepos;
        this.beanNameUrlHandlerMapping = beanNameUrlHandlerMapping;
        this.lecture_detailRepos = lecture_detailRepos;
        this.lecture_passwordRepos = lecture_passwordRepos;
        this.lecture_attendanceRepos = lecture_attendanceRepos;
        this.chatRepos = chatRepos;
    }

    @Override
    public void registerUser(String userName, String userPass) {
        int n = authUserRepos.insert(userName, userPass);
        System.out.println("記録行数：" + n);
    }

    @Override
    public void removeUser(String userName) {
        int n = authUserRepos.delete(userName);
        System.out.println("記録行数：" + n);
    }

    @Override
    public boolean existsUser(String userName, String userPass) {
        var result = authUserRepos.exists(userName, userPass);
        System.out.println(userName + ", " + userPass + " のユーザ照合結果：" + result);
        return result;
    }
    @Override
    public List<AuthUser> findAuthUsers() {
        var users = authUserRepos.find();
        System.out.println("データ件数：" + users.size());
        return users;
    }

    @Override
    public List<Subject> findSubjects() {
        var subjects = subjectRepos.find();
        System.out.println("データ件数：" + subjects.size());
        return subjects;
    }

    @Override
    public List<Lecture> findLectures() {
        var lectures = lectureRepos.find();
        System.out.println("データ件数：" + lectures.size());
        return lectures;
    }

    @Override
    public List<Lecture_detail> finddetail(String num) {
        var lectures = lecture_detailRepos.finddetails(num);
        System.out.println("データ件数：" + lectures.size());
        return lectures;
    }

    @Override
    public List<Lecture_password> findpass(String num) {
        var lectures = lecture_passwordRepos.findpass(num);
        System.out.println("データ件数：" + lectures.size());
        return lectures;
    }

    @Override
    public boolean exsitpass(String lecture_id,String username){
        var result = lecture_attendanceRepos.exsitpass(lecture_id,username);
        System.out.println(lecture_id + ", " + username + " のユーザ照合結果：" + result);
        return result;
    }

    @Override
    public void insertpass(String lecture_iD,String userName){
        int n = lecture_attendanceRepos.insertpass(lecture_iD, userName);
        System.out.println("記録行数：" + n);
    }

    public List<Chat> findmsg(){
        var chats = chatRepos.findmsg();
        System.out.println("データ件数：" + chats.size());
        return chats;
    }

    public void registpost(String userName, String userMsg){
        int n = chatRepos.insertmsg(userName, userMsg);
        System.out.println("記録行数：" + n);
    }
}