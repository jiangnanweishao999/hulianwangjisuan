<template>
    <a-modal
            :visible="updateImgVisible"
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
            <a-form-item label="path" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写path"
                        v-decorator="['path',{ rules: [{ required:true, message:'请输入path' }] }]"
                />
            </a-form-item>
            <a-form-item label="filename" v-bind="formItemLayout">
                <a-input
                        placeholder="请填写filename"
                        v-decorator="['filename',{ rules: [{ required:true, message:'请输入filename' }] }]"
                />
            </a-form-item>

        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'updateImg',
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
                "updateImgVisible"
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'updateImg' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                "set_updateImgVisible"
            ]),
            ...mapActions([
                'updateImg',
            ]),
            cancel() {
                this.set_updateImgVisible(false)
                this.reset()
            },
            handleSubmit(e) {
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            // 这里添加接口参数
                            id:this.form.getFieldValue('id'),
                            path:this.form.getFieldValue('path'),
                            filename:this.form.getFieldValue('filename'),
                        }
                        this.updateImg(data)
                        this.reset()
                        this.set_updateImgVisible(false)
                    }
                });
            },
            reset(){
                this.form.setFieldsValue({
                    'id':'',
                    'path':'',
                    'filename':''
                })
            },

        },
    }
</script>