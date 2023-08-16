import axios from "axios";

const baseurl = 'http://localhost:9090/api/'

export const login = async (data) => {
    const resp = await axios.post(baseurl + 'checklogin', data)
    console.log(resp.data);
    return resp.data;
}

export const getTopic = async () => {
    const resp = await axios.get(baseurl + 'topic');
    return resp.data;
}

export const addTopic = async (st) => {
    const resp = await axios.post(baseurl + 'topic', st)
    console.log(resp.data);
    return resp.data;
}

export const updateTopic = async (data) => {
    const resp = await axios.put(baseurl + 'topic', data)
    console.log(resp.data);
    return resp.data;
}

export const deleteTopic = async (id) => {
    const resp = await axios.delete(baseurl + 'topic/' + id)
    return resp.data;
}
// ===============================content==========================================

export const getContent = async () => {
    const resp = await axios.get(baseurl + 'content');
    return resp.data;
}

export const gettopicwisecontent = async (id) => {
    const resp = await axios.get(baseurl + 'topicwisecontents/' + id);
    return resp.data;
}

export const addContent = async (data) => {
    const added = await axios.post(baseurl + 'content', data)
    console.log(added.data);
    return added.data;
}

export const updateContent = async (data) => {
    const resp = await axios.put(baseurl + 'content', data)
    console.log(resp.data);
    return resp.data;
}

export const deleteContent = async (id) => {
    const resp = await axios.delete(baseurl + 'content/' + id);
    return resp.data;
}
// =====================================Question==============================================

export const getQuestions = async () => {
    const resp = await axios.get(baseurl + 'content_question');
    return resp.data;
}

export const addQuestion = async (data) => {
    const added = await axios.post(baseurl + 'content_question', data);
    return added.data;
}

export const updateQuestion = async (data) => {
    const update = await axios.put(baseurl + 'content_question', data);
    return update.data;
}

export const deleteQuestion = async (id) => {
    const del = await axios.delete(baseurl + 'content_question/' + id);
    return del.data;
}
// ===========================================Student=====================================================
export const getStudent = async () => {
    const getst = await axios.get(baseurl + 'studentdeatils')
    return getst.data;
}

// export const getStudentById= async (id) => {
//     const getstbyid = await axios.get(baseurl + 'studentdeatils/'+id)
//     return getstbyid.data;
// }

export const addStudent = async (data) => {
    const add = await axios.post(baseurl + 'studentdeatils', data);
    console.log(add.data);
    return add.data;
}

export const updataStudent = async (data) => {
    const update = await axios.put(baseurl + 'studentdeatils', data);
    return update.data;
}

export const getNextCode = async () => {
    const code = await axios.get(baseurl + 'nextcode');
    return code.data;
}

export const sendEmail = async () => {
    const email = await axios.post(baseurl + 'sendemail');
    return email.data;
}

export const deleteStudent = async (id) => {
    const del = await axios.delete(baseurl + 'studentdeatils/' + id);
    return del.data;
}
// ====================================ExamQuestions==========================================================

export const addExamQuestion = async (data) => {
    const add = await axios.post(baseurl + 'examquestion', data);
    return add.data;
}

export const gettopicwiseQuestion = async (id) => {
    const tq = await axios.get(baseurl + 'topicwisequestion/' + id);
    return tq.data;
}
// ======================================ExanDetails==================================================================
export const addExamDetails = async (data) => {
    const addexamdetails = await axios.post(baseurl + 'examdetails', data);
    return addexamdetails.data;
}

export const getExamDetailsByStudentId = async (id) => {
    try{
    const getexamdetails = await axios.get(baseurl + 'examdetailsbystudentid/'+id);
    return getexamdetails.data;
    }
    catch(e){
        console.error(e);
    }
}