package com.example.demo.services;

import com.example.demo.models.MyModel;
import com.example.demo.models.User;
import com.example.demo.repositories.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MyService {
    private final MyRepository myRepository;

    @Autowired
    public MyService(MyRepository myRepository) {

        this.myRepository = myRepository;
    }

    public void savingApplications(String name, String responsible, String customerInformation){
        LocalDate startDate = LocalDate.now();
        MyModel myModel = new MyModel(name, startDate, null, false, responsible, customerInformation);
        myRepository.save(myModel);
    }

    public MyModel findOne(int id) {
        Optional<MyModel> application = myRepository.findById(id);
        return application.orElse(null);
    }

    public void update(int id, MyModel myModel) {
        MyModel modelToBeUpdated = myRepository.findById(id).get();
        myModel.setId(id);
        myModel.setStartDate(modelToBeUpdated.getStartDate());
        myModel.setRecentDate(LocalDate.now());
        myModel.setStatusReadiness(modelToBeUpdated.getStatusReadiness());
        myRepository.save(myModel);
    }
    public List<MyModel> receiveApplications(){
        return myRepository.findAll();
    }

    public List<MyModel> receiveMyApplications(String responsible){
        return myRepository.findAllByResponsible(responsible);
    }
    public List<MyModel> receive(String name){
        return myRepository.findAllByName(name);
    }

    public boolean isAdmin(User user) {
        if (user.isAdmin()) {
            return true;
        }else {
            return false;
        }
    }

    public void closeApp(int id) {
        MyModel myModel =  findOne(id);
        myModel.setStatusReadiness(true);
        myRepository.save(myModel);
    }

    public void deleteApp(int id) {
        MyModel myModel =  findOne(id);
        myRepository.delete(myModel);
    }
}
