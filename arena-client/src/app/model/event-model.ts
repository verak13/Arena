export class EventModel {
    constructor(
        public id: number,
        public name: string,
        public description: string,
        public parterPrice: number,
        public westPrice: number,
        public eastPrice: number,
        public northPrice: number,
        public southPrice: number,
        public vipPrice: number,
        public startDate: any,
        public endDate: any,
        public deadline: any
    ) {
    }
}
  