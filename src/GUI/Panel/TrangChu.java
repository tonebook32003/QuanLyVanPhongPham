package GUI.Panel;

import GUI.Component.PanelShadow;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Admin
 */
public class TrangChu extends JPanel {

    JPanel top, center;
    PanelShadow content[];
    String[][] getSt = {
        {"Tính đa dạng", "tinhdadang.svg", "<html>Cửa hàng mang đến một sự đa dạng sản <br>phẩm phong phú, mở ra hàng loạt các <br>lựa chọn cho khách hàng. Với đa dạng <br>này, cửa hàng phục vụ được mọi lứa <br>tuổi và đáp ứng nhiều phong cách khác <br>nhau của khách hàng.</html>"},
        {"Tính bảo mật", "tinhbaomat.svg", "<html>Cửa hàng tự hào về tính bảo mật hàng <br>đầu, áp dụng các biện pháp an ninh <br>chặt chẽ để bảo vệ thông tin cá nhân <br>và dữ liệu quan trọng của khách hàng <br>một cách toàn diện và đáng tin cậy.</html>"},
        {"Tính giải pháp", "tinhgiaiphap.svg", "<html>Cửa hàng cung cấp không chỉ sản phẩm <br>đa dạng mà còn là một nguồn cung cấp <br>đáng tin cậy cho các giải pháp văn <br>phòng hiệu quả, giúp khách hàng tối <br>ưu hóa công việc và tăng cường năng <br>suất làm việc.</html>"},};
    Color MainColor = new Color(255, 255, 255);
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(new Color(24, 24, 24));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        top = new JPanel();
        top.setBackground(MainColor);
        top.setPreferredSize(new Dimension(1200, 350)); // Tăng chiều cao từ 250 lên 350
        top.setLayout(new BorderLayout());

        // Tạo carousel
        ImageCarousel carousel = new ImageCarousel(new String[]{
            "/img/banner1.jpg",
            "/img/banner2.png",
            "/img/banner3.jpg"
        });
        top.add(carousel, BorderLayout.CENTER);

        this.add(top, BorderLayout.NORTH);

        center = new JPanel();
        center.setBackground(BackgroundColor);
        center.setPreferredSize(new Dimension(1200, 800));
        center.setLayout(new FlowLayout(1, 40, 40));
        content = new PanelShadow[getSt.length];
        for (int i = 0; i < getSt.length; i++) {
            content[i] = new PanelShadow(getSt[i][1], getSt[i][0], getSt[i][2]);
            center.add(content[i]);
        }

        this.add(center, BorderLayout.CENTER);
    }

    public TrangChu() {
        initComponent();
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
    }

    class ImageCarousel extends JPanel {

        private JLabel imageLabel;
        private String[] imagePaths;
        private int currentIndex;
        private Timer timer;

        public ImageCarousel(String[] imagePaths) {
            this.imagePaths = imagePaths;
            this.currentIndex = 0;

            setLayout(new BorderLayout());
            imageLabel = new JLabel();
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            add(imageLabel, BorderLayout.CENTER);

            loadNextImage();

            timer = new Timer(3000, new ActionListener() { // Chuyển đổi hình ảnh mỗi 3 giây
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadNextImage();
                }
            });
            timer.start();
        }

        private void loadNextImage() {
            ImageIcon icon = new ImageIcon(getClass().getResource(imagePaths[currentIndex]));
            Image img = icon.getImage().getScaledInstance(1200, 350, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(img));
            currentIndex = (currentIndex + 1) % imagePaths.length;
        }
    }
}
