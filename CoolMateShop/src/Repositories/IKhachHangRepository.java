package Repositories;

import DomainModel.KhachHang;
import java.util.ArrayList;
import java.util.List;

public interface IKhachHangRepository {

    List<KhachHang> getAll();

    Integer insert(KhachHang kh);

    Integer update(KhachHang kh, String id);

    String findMa(String ma);
    
    String findName(String sdt);

    ArrayList<KhachHang> timTheoTen(String hoTen);
}
