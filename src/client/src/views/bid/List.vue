<template>
  <div class="bid-list">
    <b-row>
      <b-col cols="12">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
          <h1 class="h2">Bid</h1>
          <b-button-toolbar>
            <b-button-group>
              <b-button :to="{ name: 'bid-create' }">New</b-button>
            </b-button-group>
          </b-button-toolbar>
        </div>
        <TblData />
      </b-col>
    </b-row>
  </div>
</template>

<script>
import TblData from '../../components/bid/TblData.vue';
export default {
  components: {
    TblData
  }
}
</script>