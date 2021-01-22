<template>
  <div class="tbl-bid">
    <b-table
      id="tbl-bid"
      bordered striped hover
      :busy="isBusy"
      :items="items"
      :fields="fields">
      <template #table-busy>
        <div class="text-center text-danger my-2">
          <b-spinner class="align-middle"></b-spinner>
          <strong>Loading...</strong>
        </div>
      </template>
      <template #cell(actions)="row">
        <div class="text-center">
          <b-button class="ml-1 mr-1" size="sm" :to="{ name: 'bid-detail', params: { id: row.item.id } }">View</b-button>
          <b-button class="ml-1 mr-1" size="sm" :to="{ name: 'bid-update', params: { id: row.item.id } }">Update</b-button>
        </div>
      </template>
    </b-table>
    <b-pagination
      :disabled="isBusy"
      v-model="currentPage"
      :per-page="pageSize"
      :total-rows="totalItems"
      aria-controls="tbl-bid"
      @change="onPageChanged">
    </b-pagination>
  </div>
</template>

<script>
  import api from '../../api';
  import moment from "moment";
  export default {
    data() {
      return {
        isBusy: true,
        fields: [
          {
            key: 'name',
            label: 'Title',
            formatter: (value) => {
              return value;
            }
          },
          {
            key: 'priceMin',
            label: 'Min',
            formatter: (value) => {
              return Number(value).toLocaleString();
            }
          },
          {
            key: 'priceMax',
            label: 'Max',
            formatter: (value) => {
              return Number(value).toLocaleString();
            }
          },
          {
            key: 'priceStep',
            label: 'Step',
            formatter: (value) => {
              return Number(value).toLocaleString();
            }
          },
          {
            key: 'endTime',
            label: 'End Time',
            formatter: (value) => {
              return moment(value * 1000).fromNow();
            }
          },
          { key: 'actions', label: 'Actions' }
        ],
        items: [],
        pageSize: 10,
        currentPage: 1,
        totalItems: 0
      }
    },
    mounted() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.isBusy = true;
        api.bid.gets(this.currentPage - 1, this.pageSize)
          .then(response => {
            if (response.status == 200) {
              this.items = response.data.items
              this.pageSize = response.data.pageSize
              this.totalItems = response.data.totalItems
              this.currentPage = response.data.currentPage + 1
            }
          })
          .catch(error => {
            this.$log.debug(error)
          })
          .finally(() => { this.isBusy = false })
      },
      onPageChanged(value) {
        this.currentPage = value;
        this.fetchData();
      }
    }
  }
</script>
