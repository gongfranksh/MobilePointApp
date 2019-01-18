package personal.wl.mobilepointapp.listener;

import java.util.List;

import personal.wl.mobilepointapp.entity.pos.Product;

public interface RequestCallBack {
    void success (List<Product> data);
    void fail(Exception e);
}
