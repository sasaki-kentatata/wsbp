package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Subject;
import com.example.wsbp.repository.IAuthUserRepository;
import com.example.wsbp.repository.ISubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private IAuthUserRepository authUserRepos;
    private ISubjectRepository subjectRepos;

    @Autowired
    public UserService(IAuthUserRepository authUserRepos,ISubjectRepository subjectRepos) {
        this.authUserRepos = authUserRepos;
        this.subjectRepos = subjectRepos;
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
}