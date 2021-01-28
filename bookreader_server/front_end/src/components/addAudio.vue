<template>
    <a-modal
            :visible="addAudioVisible"
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
            <a-form-item label="audioName" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写audioName"
                        v-decorator="['audioName',{ rules: [{ required:true, message:'请输入audioName' }] }]"
                />
            </a-form-item>
            <a-form-item label="bookId" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写bookId"
                        v-decorator="['bookId',{ rules: [{ required:true, message:'bookId' }] }]"
                />
            </a-form-item>
            <a-form-item label="audioNo" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写音频编号"
                        v-decorator="['audioNo',{ rules: [{ required:true, message:'audioNo' }] }]"
                />
            </a-form-item>
            <a-form-item label="audioDescription" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写简介"
                        v-decorator="['audioDescription',{ rules: [{ required:true, message:'audioDescription' }] }]"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addAudio',
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
                "addAudioVisible"
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'addBook' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                "set_addAudioVisible"
            ]),
            ...mapActions([
                'addAudio'
            ]),
            cancel() {
                this.set_addAudioVisible(false)
                this.reset()
            },
            handleSubmit(e) {
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            sourcePath:this.form.getFieldValue('sourcePath'),
                            audioName:this.form.getFieldValue('audioName'),
                            bookId:Number(this.form.getFieldValue('bookId')),
                            audioNo:Number(this.form.getFieldValue('audioNo')),
                            audioDescription:(this.form.getFieldValue('audioDescription'))
                        }
                        this.addAudio(data)
                        //this.reset()
                        this.set_addAudioVisible(false)
                    }
                });
            },
            reset(){
                this.form.setFieldsValue({
                    'sourcePath':'',
                    'audioName':'',
                    'bookId':'',
                    'audioNo':'',
                    'audioDescription':'',
                })
            },

        },
    }
</script>