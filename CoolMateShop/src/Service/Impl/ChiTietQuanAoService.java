/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service.Impl;

import DomainModel.ChiTietQuanAo;
import Repositories.IChiTietQuanAoRepository;
import Repositories.Impl.ChiTietQuanAoRepository;
import Service.IChiTietQuanAoService;
import ViewModel.ChiTietQuanAoRespone;
import java.util.List;

/**
 *
 * @author KIEN TRAN
 */
public class ChiTietQuanAoService implements IChiTietQuanAoService {

    private IChiTietQuanAoRepository chiTietQuanAoRepository;

    public ChiTietQuanAoService() {
        chiTietQuanAoRepository = new ChiTietQuanAoRepository();
    }

    @Override
    public List<ChiTietQuanAoRespone> getAllCTQA() {
        return chiTietQuanAoRepository.getAll();
    }

    @Override
    public Integer insert(ChiTietQuanAo ctqa) {
        try {
            return chiTietQuanAoRepository.insert(ctqa);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer update(ChiTietQuanAo ctqa, String ma) {
        try {
            return chiTietQuanAoRepository.update(ctqa, ma);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer delete(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ChiTietQuanAoRespone> searchByMa(String ma) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String checkMa(String ma) {
        try {
            return chiTietQuanAoRepository.checkMa(ma);
        } catch (Exception e) {
            return null;
        }
    }

}