<template>
  <div>
    <a-tree
      v-model="checkedKeys"
      :expanded-keys="expandedKeys"
      :auto-expand-parent="autoExpandParent"
      :selected-keys="selectedKeys"
      :tree-data="treeData"
      @expand="onExpand"
      showLine
    >
      <template v-slot:title="nodeData">
        <span>{{ nodeData.title }}</span>
        <a-button-group style="margin-left: 200px; float: right"
          ><a-button
            @click="addSameCategory(nodeData)"
            size="small"
            icon="plus-circle"
            title="添加同级"
          ></a-button>
          <a-button
            @click="addNextCategory(nodeData)"
            size="small"
            icon="share-alt"
            title="添加下级"
          ></a-button>
          <a-button
            @click="updateCategory(nodeData)"
            size="small"
            icon="form"
            title="修改"
          ></a-button>
          <a-button
            size="small"
            @click="del(nodeData.key)"
            icon="close-circle"
            title="删除"
          ></a-button
        ></a-button-group>
      </template>
    </a-tree>
    <div>
      <a-modal v-model="visible" :title="modalTitle" @ok="handleOk">
        <a-input :value="title" placeholder="标题" />
        <a-input :value="parentCid" style="display: none" />
        <a-input :value="catLevel" style="display: none" />
      </a-modal>
    </div>
  </div>
</template>

<script>
var treeData = [];
var type = 1;
export default {
  created() {
    this.setTreeData();
  },
  data() {
    return {
      visible: false,
      modalTitle: "",
      id: null,
      title: null,
      parentCid: null,
      catLevel: null,
      expandedKeys: [],
      autoExpandParent: true,
      checkedKeys: [],
      selectedKeys: [],
      treeData,
    };
  },
  watch: {
    checkedKeys(val) {
      console.log("onCheck", val);
    },
  },
  methods: {
    onExpand(expandedKeys) {
      console.log("onExpand", expandedKeys);
      // if not set autoExpandParent to false, if children expanded, parent can not collapse.
      // or, you can remove all expanded children keys.
      this.expandedKeys = expandedKeys;
      this.autoExpandParent = false;
    },
    onCheck(checkedKeys) {
      console.log("onCheck", checkedKeys);
      this.checkedKeys = checkedKeys;
    },
    // onSelect(selectedKeys, info) {
    //   console.log("onSelect", info);
    //   this.selectedKeys = selectedKeys;
    // },
    setTreeData: function () {
      this.axios.get("product/category/treeList").then((response) => {
        console.log(response.data);
        if (response.data.data != null && response.data.code == 200) {
          this.treeData = this.getTreeList(response.data.data);
        }
      });
    },
    getTreeList: function (tree) {
      var treeList = [];
      tree.forEach((node) => {
        let newTree = {
          title: node.name,
          key: node.catId,
          parentCid: node.parentCid,
          catLevel: node.catLevel,
          scopedSlots: {
            title: "title",
          },
        };
        if (node.child != null) {
          newTree.children = this.getTreeList(node.child);
        }
        treeList.push(newTree);
      });
      return treeList;
    },
    showModal() {
      this.visible = true;
    },
    addSameCategory(category) {
      console.log(category);
      this.modalTitle = "添加同级菜单";
      this.visible = true;
      this.parentCid = category.parentCid;
      this.catLevel = category.catLevel;
    },
    addNextCategory(category) {
      console.log(category);
      this.modalTitle = "添加下一级菜单";
      this.visible = true;
      this.parentCid = category.key;
      this.catLevel = category.catLevel + 1;
    },
    updateCategory(category) {
      console.log(category);
      type = 2;
      this.modalTitle = "修改菜单";
      this.visible = true;
      this.id = category.key;
      this.title = category.title;
      this.parentCid = category.parentCid;
      this.catLevel = category.catLevel;
    },
    handleOk(e) {
      console.log(e);
      this.visible = false;
      switch (type) {
        case 1:
          this.axios({
            url: "product/category/add",
            method: "post",
            header: {
              "Content-Type": "application/json", //如果写成contentType会报错
            },
            data: {
              name: this.title,
              catLevel: this.catLevel,
              parentCid: this.parentCid,
            },
          }).then((response) => {
            if (response.data.code == 200) {
              alert(this.modalTitle + "成功！");
              this.setTreeData();
            } else {
              alert(this.modalTitle + "失败！");
            }
          });
          break;
        case 2:
          this.axios({
            url: "product/category/update",
            method: "put",
            header: {
              "Content-Type": "application/json", //如果写成contentType会报错
            },
            data: {
              catId: this.id,
              name: this.title,
              catLevel: this.catLevel,
              parentCid: this.parentCid,
            },
          }).then((response) => {
            if (response.data.code == 200) {
              alert(this.modalTitle + "成功！");
              this.setTreeData();
            } else {
              alert(this.modalTitle + "失败！");
            }
          });
          break;
      }
    },
    del: function (id) {
      console.log(id);
      this.axios.get("product/category/canDel/" + id).then((response) => {
        if (response.data.data) {
          this.axios.delete("product/category/del/" + id).then((response) => {
            if (response.data.code == 200) {
              alert("删除成功！");
              this.setTreeData();
            } else {
              alert("删除失败！");
            }
          });
        } else {
          alert("很抱歉，该节点有子节点，不能删除！");
        }
      });
    },
  },
};
</script>