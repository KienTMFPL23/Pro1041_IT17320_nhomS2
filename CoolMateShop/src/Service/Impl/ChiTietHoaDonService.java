/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.ChiTietHoaDon;
import Repositories.IChiTietHoaDonRepository;
import Repositories.Impl.ChiTietHoaDonRepository;
import Service.IChiTietHoaDonService;
import ViewModel.ChiTietHoaDonRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public class ChiTietHoaDonService implements IChiTietHoaDonService {

    private IChiTietHoaDonRepository cthdRepo;

    public ChiTietHoaDonService() {
        cthdRepo = new ChiTietHoaDonRepository();
    }

    @Override
    public List<ChiTietHoaDonRespone> getAll() {
        return cthdRepo.getAll();
    }

    @Override
    public Integer insert(ChiTietHoaDon cthd) {
        try {
            return cthdRepo.insert(cthd);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public List<ChiTietHoaDonRespone> getAllByMa(String ma) {
        return cthdRepo.getAllByMa(ma);
    }

    @Override
    public List<ChiTietHoaDonRespone> getListHD(String id) {
        return cthdRepo.getAllHD(id);
    }

    @Override
    public Integer delete(String id) {
        try {
            return cthdRepo.delete(id);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer updateSLuong(String id, int soLuong) {
        try {
            return cthdRepo.update(id, soLuong);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer getSlMua(String id) {
        try {
            return cthdRepo.getSLMua(id);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String getIdQA(String id, String idqa) {
        try {
            return cthdRepo.getIdQA(id, idqa);
        } catch (Exception e) {
            return null;
        }
    }

}
