import { onMounted, reactive, onBeforeUnmount } from "vue";

export default function () {
    let position = reactive({
        x: 0,
        y: 0,
    });

    function getMousePoint(event) {
        position.x = event.pageX;
        position.y = event.pageY;
        console.log(event.pageX, event.pageY);
    }

    //挂载函数
    onMounted(() => {
        window.addEventListener('click', getMousePoint);
    });

    //卸载函数
    onBeforeUnmount(() => {
        window.removeEventListener('click', getMousePoint);
    });

    return position;
}