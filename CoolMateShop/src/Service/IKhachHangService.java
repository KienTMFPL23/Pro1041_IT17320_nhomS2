package Service;

import DomainModel.KhachHang;
import java.util.ArrayList;
import java.util.List;

public interface IKhachHangService {

    List<KhachHang> getList();

    Integer insert(KhachHang kh);

    Integer update(KhachHang kh, String id);

    String checkMa(String ma);

    ArrayList<KhachHang> timTheoTen(String hoTen);
}
