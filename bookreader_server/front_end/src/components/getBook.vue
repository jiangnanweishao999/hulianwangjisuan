<template>
    <a-modal
            :visible="getBookVisible"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <!-- 这里是添加策略模态框区域，请编写表单 -->
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="book" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写bookId"
                        v-decorator="['book',{ rules: [{ required:true, message:'请输入bookId' }] }]"
                />
            </a-form-item>
            <a-form-item label="chapter" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写ChapterNo"
                        v-decorator="['chapter',{ rules: [{ required:true, message:'请输入ChapterNo' }] }]"
                />
            </a-form-item>
            <a-form-item label="line" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写lineLimit"
                        v-decorator="['line',{ rules: [{ required:true, message:'请输入lineLimit' }] }]"
                />
            </a-form-item>
            <a-form-item label="word" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写wordLimit"
                        v-decorator="['word',{ rules: [{ required:true, message:'请输入wordLimit' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'getBook',
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
                "getBookVisible"
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'getBook' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                "set_getBookVisible"
            ]),
            ...mapActions([
                'getBook',
            ]),
            cancel() {
                this.set_getBookVisible(false)
                this.reset()
            },
             handleSubmit (e) {
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const param = {
                            // 这里添加接口参数
                           book:Number(this.form.getFieldValue('book')),
                           chapter:Number(this.form.getFieldValue('chapter')),
                           line:Number(this.form.getFieldValue('line')),
                           word:Number(this.form.getFieldValue('word')),
                        }
                        this.getBook(param)
                        this.reset()
                        this.set_getBookVisible(false)
                    }
                });
            },
            reset(){
                this.form.setFieldsValue({
                    'book':'',
                    'chapter':'',
                    'line':'',
                    'word':'',
                })
            },

        },
    }
</script>