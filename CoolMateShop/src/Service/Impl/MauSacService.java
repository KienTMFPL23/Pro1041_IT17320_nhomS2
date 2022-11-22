/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.MauSac;
import Repositories.IMauSacRepository;
import Repositories.Impl.MauSacRepository;
import Service.IMauSacService;
import ViewModel.MauSacRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public class MauSacService implements IMauSacService {

    private IMauSacRepository msRepository;

    public MauSacService() {
        msRepository = new MauSacRepository();
    }

    @Override
    public Integer insert(MauSac ms) {
        try {
            return msRepository.insert(ms);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer update(MauSac ms, String ma) {
        try {
            return msRepository.update(ms, ma);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer delete(String ma) {
        try {
            return msRepository.delete(ma);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String checkMa(String ma) {
        try {
            return msRepository.checkMa(ma);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<MauSacRespone> getAllMau() {
        return msRepository.getAllMau();
    }

    @Override
    public MauSac getOne(String id) {
        return msRepository.getOne(id);
    }

    @Override
    public List<MauSac> getAll() {
        return msRepository.getList();
    }

}
