package cn.dolphinsoft.hilife.authority.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: TreeNodeBean
 * 
 * Description: 节点树
 * 
 * @author zhishenghong
 *
 */
public class TreeNodeBean {

    private Integer id;

    private String nodeName;

    private Integer level;

    private String nodeDesc;

    private Integer pid;

    private List<TreeNodeBean> childrenList = new ArrayList<TreeNodeBean>();

    private TreeNodeBean parentBean;

    private boolean isLeaf;

    /**
     * @return return the value of the var id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Description: Set id value
     * 
     * @param id
     * 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return return the value of the var nodeName
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * Description: Set nodeName value
     * 
     * @param nodeName
     * 
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    /**
     * @return return the value of the var level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Description: Set level value
     * 
     * @param level
     * 
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * @return return the value of the var nodeDesc
     */
    public String getNodeDesc() {
        return nodeDesc;
    }

    /**
     * Description: Set nodeDesc value
     * 
     * @param nodeDesc
     * 
     */
    public void setNodeDesc(String nodeDesc) {
        this.nodeDesc = nodeDesc;
    }

    /**
     * @return return the value of the var pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * Description: Set pid value
     * 
     * @param pid
     * 
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * @return return the value of the var childrenList
     */
    public List<TreeNodeBean> getChildrenList() {
        return childrenList;
    }

    /**
     * Description: Set childrenList value
     * 
     * @param childrenList
     * 
     */
    public void setChildrenList(List<TreeNodeBean> childrenList) {
        this.childrenList = childrenList;
    }

    /**
     * @return return the value of the var parentBean
     */
    public TreeNodeBean getParentBean() {
        return parentBean;
    }

    /**
     * Description: Set parentBean value
     * 
     * @param parentBean
     * 
     */
    public void setParentBean(TreeNodeBean parentBean) {
        this.parentBean = parentBean;
    }

    /**
     * @return return the value of the var isLeaf
     */
    public boolean isLeaf() {
        return isLeaf;
    }

    /**
     * Description: Set isLeaf value
     * 
     * @param isLeaf
     * 
     */
    public void setLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }
}
