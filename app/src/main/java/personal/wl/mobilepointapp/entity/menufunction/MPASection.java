package personal.wl.mobilepointapp.entity.menufunction;

import com.chad.library.adapter.base.entity.SectionEntity;

import java.util.List;

public class MPASection extends SectionEntity<MPAFunction> {
    private Integer centid;
    private Integer sectionid;
    private String sectionname;
    private List<MPAFunction> functions;

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean more) {
        isMore = more;
    }

    private boolean isMore;

    public MPASection(boolean isHeader, String header, Boolean isMore) {
        super(isHeader, header);
        this.isMore = isMore;
    }

    public MPASection(MPAFunction functions) {
        super(functions);
    }

    public Integer getCentid() {
        return centid;
    }

    public void setCentid(Integer centid) {
        this.centid = centid;
    }

    public Integer getSectionid() {
        return sectionid;
    }

    public void setSectionid(Integer sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }

    public List<MPAFunction> getFunctions() {
        return functions;
    }

    public void setFunctions(List<MPAFunction> functions) {
        this.functions = functions;
    }
}
