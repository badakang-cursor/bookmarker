import { BookmarksResponse } from '@/app/types/bookmark';
import axios from 'axios';
import React from 'react';

// const API_BASE_URL = "http://localhost:8080";
const getApiUrl = () =>{
    // 서버 사이드 URL(도커로 배포된 서버사이트 호출시 사용)
    const serverApiUrl = process.env.SERVER_SIDE_API_BASE_URL;

    // 클라이언트 사이드 URL(브라우저에서 호출할 공개용 API)
    const clientApiUrl = process.env.NEXT_PUBLIC_CLIENT_SIDE_API_BASE_URL;
    
    // if(typeof window === undefined){
    //     console.log('>>> window 객체(undefined) : ', window);
    // }
    // if(typeof window === 'undefined'){
    //     console.log('>>> window 문자열(undefined) : ', window);
    // }

    if(typeof window === 'undefined'){
        console.log('>>> 서버 사이드 실행 - 사용 URL : ', serverApiUrl);
        return serverApiUrl || clientApiUrl;
    }

    console.log('>>> 클라이언트 사이드 실행 - 사용 URL : ', clientApiUrl);
    return clientApiUrl;

}


export const fetchBookmarks = async(page:number, query ?: string): Promise<BookmarksResponse> => {
    let apiUrl = getApiUrl();
    console.log('>>> 실제 URL[1][1] : ', apiUrl);
    apiUrl = (apiUrl === undefined) ?  '' : apiUrl;
    console.log('>>> 실제 URL[1][2] : ', apiUrl);
    const resp = await axios.get<BookmarksResponse>(apiUrl.concat('/api/bookmarks?page=').concat(String(page)).concat('&query=').concat(String(query)));
    
  return resp.data;
}

export const saveBookmark = async(bookmark:{title:string, url:string}) => {
    try{
        let apiUrl = getApiUrl();
    console.log('>>> 실제 URL[2][1] : ', apiUrl);
    apiUrl = (apiUrl === undefined) ?  '' : apiUrl;
    console.log('>>> 실제 URL[2][2] : ', apiUrl);
        const resp = await axios.post(apiUrl.concat('/api/bookmarks'), bookmark);
        return resp.data; //성공시 응답
    } catch (error){
        console.log('Error saving Bookmark fail', error);
        throw new Error("북마크 저장 실패");
    }
}
