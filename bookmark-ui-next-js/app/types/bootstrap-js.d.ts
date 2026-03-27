// BootstrapClient.tsx 파일의 아래 오류로 때문에 생성
// - 모듈 'bootstrap/dist/js/bootstrap.min.js'에 대한 선언 파일을 찾을 수 없습니다. 'c:/dev/projects/java/fullstackcamp/bookmarker/bookmark-ui-next-js/node_modules/bootstrap/dist/js/bootstrap.min.js'에는 암시적으로 'any' 형식이 포함됩니다.
// - 해당 항목이 있는 경우 'npm i --save-dev @types/bootstrap'을(를) 시도하거나, 'declare module 'bootstrap/dist/js/bootstrap.min.js';'을(를) 포함하는 새 선언(.d.ts) 파일 추가ts(7016)

// import오류 - 타입이 정의되어 있지 않기 때문에 any 타입으로 설정
declare module 'bootstrap/dist/js/bootstrap.min.js'{
    const bootstrap : any;
    export default bootstrap;
}


