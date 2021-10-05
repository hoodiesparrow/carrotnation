<script>
import { useRoute } from 'vue-router'

export default {
  setup() {
    const route = useRoute()
    console.log(route.path)

  },
}
</script>
