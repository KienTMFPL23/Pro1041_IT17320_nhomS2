package Service.Impl;

import DomainModel.KhachHang;
import Repositories.Impl.KhachHangRepository;
import Service.IKhachHangService;
import java.util.ArrayList;
import java.util.List;

public class KhachHangService implements IKhachHangService {

    private KhachHangRepository khRepo;

    public KhachHangService() {
        khRepo = new KhachHangRepository();
    }

    @Override
    public List<KhachHang> getList() {
        return khRepo.getAll();
    }

    @Override
    public Integer insert(KhachHang kh) {
        try {
            return khRepo.insert(kh);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer update(KhachHang kh, String id) {
        try {
            return khRepo.update(kh, id);
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String checkMa(String ma) {
        try {
            return khRepo.findMa(ma);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<KhachHang> timTheoTen(String hoTen) {
        return this.khRepo.timTheoTen(hoTen);
    }

    @Override
    public String findName(String sdt) {
        try {
            return khRepo.findName(sdt);
        } catch (Exception e) {
            return null;
        }
    }

}
