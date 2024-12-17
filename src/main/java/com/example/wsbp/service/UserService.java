package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Lecture;
import com.example.wsbp.data.Lecture_detail;
import com.example.wsbp.data.Subject;
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

    @Autowired
    public UserService(IAuthUserRepository authUserRepos, ISubjectRepository subjectRepos, ILectureRepository lectureRepos, ILecture_detailRepository lecture_detailRepos, BeanNameUrlHandlerMapping beanNameUrlHandlerMapping) {
        this.authUserRepos = authUserRepos;
        this.subjectRepos = subjectRepos;
        this.lectureRepos = lectureRepos;
        this.beanNameUrlHandlerMapping = beanNameUrlHandlerMapping;
        this.lecture_detailRepos = lecture_detailRepos;
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
}