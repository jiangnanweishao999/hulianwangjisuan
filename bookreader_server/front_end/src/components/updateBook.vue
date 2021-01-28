<template>
    <a-modal
            :visible="updateBookVisible"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="id" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写id"
                        v-decorator="['id',{ rules: [{ required:true, message:'请输入id' }] }]"
                />
            </a-form-item>
            <a-form-item label="bookAuthor" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写bookAuthor"
                        v-decorator="['bookAuthor',{ rules: [{ required:true, message:'请输入bookAuthor' }] }]"
                />
            </a-form-item>
            <a-form-item label="bookDescription" v-bind="formItemLayout">
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写bookDescription"
                        v-decorator="['bookDescription',{ rules: [{ required:true, message:'请输入bookDescription' }] }]"
                />
            </a-form-item>
            <a-form-item label="label" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写label"
                        v-decorator="['label',{ rules: [{ required:true, message:'请输入label' }] }]"
                />
            </a-form-item>

        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'updateBook',
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
                "updateBookVisible"
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'updateBook' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                "set_updateBookVisible"
            ]),
            ...mapActions([
                'updateBook',
            ]),
            cancel() {
                this.set_updateBookVisible(false)
                this.reset()
            },
            handleSubmit(e) {
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            id:this.form.getFieldValue('id'),
                            bookAuthor:this.form.getFieldValue('bookAuthor'),
                            bookDescription:this.form.getFieldValue('bookDescription'),
                            label:this.form.getFieldValue('label'),
                        }
                        this.updateBook(data)
                        this.reset()
                        this.set_updateBookVisible(false)
                    }
                });
            },
            reset(){
                this.form.setFieldsValue({
                    'id':'',
                    'bookAuthor':'',
                    'bookDescription':'',
                    'label':''
                })
            },

        },
    }
</script>