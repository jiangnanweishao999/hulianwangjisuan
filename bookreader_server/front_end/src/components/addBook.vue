<template>
    <a-modal
            :visible="addBookVisible"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="sourcePath" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写sourcePath"
                        v-decorator="['sourcePath',{ rules: [{ required:true, message:'请输入sourcePath' }] }]"
                />
            </a-form-item>
            <a-form-item label="bookName" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写bookName"
                        v-decorator="['bookName',{ rules: [{ required:true, message:'请输入bookName' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addBook',
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
            }
        },
        computed: {
            ...mapGetters([
            "addBookVisible"
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'addBook' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                "set_addBookVisible"
            ]),
            ...mapActions([
                'addBook',
            ]),
            cancel() {
                this.set_addBookVisible(false)
                this.reset()
            },
            handleSubmit(e) {
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            sourcePath:this.form.getFieldValue('sourcePath'),
                            bookName:this.form.getFieldValue('bookName'),
                             }
                        this.addBook(data)
                        this.reset()
                        this.set_addBookVisible(false)
                    }
                });
            },
            reset(){
                this.form.setFieldsValue({
                    'sourcePath':'',
                    'bookName':''
                })
            },

        },
    }
</script>