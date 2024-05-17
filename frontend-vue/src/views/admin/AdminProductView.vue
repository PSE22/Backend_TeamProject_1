<template>
  <div>
    <!-- TODO: 여기 -->
    <body id="page-top">
      <!-- Page Wrapper -->
      <div id="wrapper">
        <!-- TODO: 왼쪽 메뉴 -->
        <ul
          class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion"
          id="accordionSidebar"
        >
          <!-- Sidebar - Brand -->
          <a
            class="sidebar-brand d-flex align-items-center justify-content-center"
            href="/admin-product"
          >
            <div class="sidebar-brand-icon rotate-n-15"></div>
            <div class="sidebar-brand-text mx-3 logo">서울쥐</div>
          </a>

          <!-- Divider -->
          <hr class="sidebar-divider my-0" />

          <!-- Nav Item - Dashboard -->
          <li class="nav-item active">
            <a class="nav-link" href="/admin-product">
              <i class="fas fa-fw fa-tachometer-alt"></i>
              <span>처음으로</span></a
            >
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider" />

          <!-- Nav Item - Pages Collapse Menu -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-order">
              <i class="fas fa-fw fa-table"></i>
              <span>주문 관리</span></a
            >
          </li>

          <!-- Nav Item - Utilities Collapse Menu -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-product">
              <i class="fas fa-fw fa-table"></i>
              <span>상품 관리</span></a
            >
          </li>

          <!-- Divider -->
          <hr class="sidebar-divider" />

          <!-- Nav Item - Pages Collapse Menu -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-coupon">
              <i class="fas fa-fw fa-table"></i>
              <span>쿠폰관리</span></a
            >
          </li>

          <!-- Nav Item - Charts -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-chart">
              <i class="fas fa-fw fa-chart-area"></i>
              <span>통계</span></a
            >
          </li>

          <!-- Nav Item - Tables -->
          <li class="nav-item">
            <a class="nav-link" href="/admin-pdqna">
              <i class="fas fa-fw fa-table"></i>
              <span>문의관리</span></a
            >
          </li>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
          <!-- Main Content -->
          <div id="content">
            <!-- TODO: 상단 메뉴 시작 -->
            <nav
              class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
            >
              <!-- 좌측상단 검색 -->
              <form
                class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
              >
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control bg-light border-0 small"
                    placeholder="상품관리 검색"
                    aria-label="Search"
                    aria-describedby="basic-addon2"
                    v-model="searchPdName"
                  />
                  <div class="input-group-append">
                    <button
                      class="btn btn-primary"
                      type="button"
                      @click="retrieveAdminProduct"
                    >
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
            </nav>
            <!-- 상단 메뉴 끝 -->

            <!-- TODO: 상품 관리 시작 -->
            <div class="container-fluid">
              <!-- Page Heading -->
              <h1 class="h3 mb-2 text-gray-800">상품관리</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">상품관리</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <div v-if="!submitted">
                      <!-- TODO: 등록 시작 -->
                      <button
                        type="button"
                        class="btn btn-primary mr-3 mb-3"
                        data-bs-toggle="modal"
                        :data-bs-target="'#exampleModal-1'"
                      >
                        등록
                      </button>

                      <!-- 등록 모달 -->
                      <div class="modal fade" id="exampleModal-1" tabindex="-1">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h1
                                class="modal-title fs-5"
                                id="exampleModalLabel"
                              >
                                상품 등록
                              </h1>
                              <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                              ></button>
                            </div>
                            <!-- 등록 모달 내 목록 -->
                            <div class="modal-body">
                              <!-- 회원아이디 -->
                              <h3 class="fs-5 mt-1">회원아이디</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                disabled
                                type="text"
                                class="form-control mb-4"
                                name="text"
                                v-model="adminProductData.userId"
                              />
                              <!-- 상품명 -->
                              <h3 class="fs-5 mt-1">상품명</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="상품명을 입력하세요"
                                name="text"
                                v-model="adminProductData.pdName"
                              />
                              <!-- 가격 -->
                              <h3 class="fs-5 mt-3">가격</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="가격을 입력하세요"
                                name="text"
                                v-model="adminProductData.pdPrice"
                              />
                              <!-- 재고 -->
                              <h3 class="fs-5 mt-3">재고</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="재고수량을 입력하세요"
                                name="text"
                                v-model="adminProductData.pdStock"
                              />
                              <!-- 카테고리분류코드 -->
                              <h3 class="fs-5 mt-3">카테고리분류코드</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="카테고리분류코드를 입력하세요"
                                name="text"
                                v-model="adminProductData.categoryCode"
                              />
                              <!-- 썸네일URL -->
                              <h3 class="fs-5 mt-3">썸네일URL</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="썸네일URL을 입력하세요"
                                name="text"
                                v-model="adminProductData.pdThumbnail"
                              />
                              <!-- 상태 확인 시작 -->
                              <h3 class="fs-5 mt-3">상태</h3>
                              <div class="form-check form-check-inline mt-3">
                                <label
                                  for="form-check-label"
                                  class="inlineRadio1"
                                ></label>
                                <input
                                  type="radio"
                                  class="form-check-input mb-4"
                                  name="Y"
                                  value="활성"
                                  v-model="adminProductData.status"
                                />
                                <label
                                  class="form-check-label"
                                  for="inlineRadio1"
                                  >활성</label
                                >
                              </div>
                              <div class="form-check form-check-inline">
                                <label
                                  for="form-check-label"
                                  class="inlineRadio2"
                                ></label>
                                <input
                                  type="radio"
                                  class="form-check-input mb-4"
                                  name="N"
                                  value="비활성"
                                  v-model="adminProductData.status"
                                />
                                <label
                                  class="form-check-label"
                                  for="inlineRadio2"
                                  >비활성</label
                                >
                              </div>
                              <!-- 상태 확인 끝 -->
                            </div>
                            <div class="modal-footer">
                              <button
                                type="submit"
                                class="btn btn-primary"
                                @click="saveAdminProductData"
                              >
                                등록
                              </button>
                              <button
                                type="button"
                                class="btn btn-secondary"
                                data-bs-dismiss="modal"
                              >
                                닫기
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- 등록 모달 끝 -->

                      <!-- TODO: 등록 끝 -->
                      <div class="row">
                        <div class="col-sm-12">
                          <table
                            class="table table-bordered dataTable"
                            width="100%"
                            cellspacing="0"
                            role="grid"
                            aria-describedby="dataTable_info"
                            style="width: 100%"
                          >
                            <thead>
                              <tr role="row">
                                <th>액션</th>
                                <th>상품번호</th>
                                <th>상품명</th>
                                <th>가격</th>
                                <th>재고</th>
                                <th>카테고리분류코드</th>
                                <th>썸네일URL</th>
                                <th>저장일</th>
                                <th>수정일</th>
                                <th>삭제일</th>
                                <th>상태</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="(data, index) in adminProduct"
                                :key="index"
                              >
                                <td>
                                  <!-- TODO: 링크 : a 태그 (전체 새로고침(성능저하) -> 페이지전환) -->
                                  <!-- TODO: 뷰에서제공 링크 : router-link (부분 새로고침: 성능향상) -->
                                  <router-link
                                    :to="'/admin-product-edit/' + data.pdId"
                                  >
                                    <span class="badge text-bg-secondary"
                                      >수정</span
                                    >
                                  </router-link>
                                </td>
                                <td>{{ data.pdId }}</td>
                                <td>{{ data.pdName }}</td>
                                <td>{{ data.pdPrice }}</td>
                                <td>{{ data.pdStock }}</td>
                                <td>{{ data.categoryCode }}</td>
                                <td>{{ data.pdThumbnail }}</td>
                                <td>{{ data.addDate }}</td>
                                <td>{{ data.modDate }}</td>
                                <td>{{ data.delDate }}</td>
                                <td>{{ data.status }}</td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 시작 / 페이지 네이션 백에서 해놓음 -->
                    <div class="row">
                      <div class="col-sm-12 col-md-5">
                        <div
                          class="dataTables_info"
                          role="status"
                          aria-live="polite"
                        >
                          검색결과 총 {{ productCount }} 건
                        </div>
                      </div>
                      <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_Simple_numbers">
                          <b-pagination
                            v-model="productPage"
                            :total-rows="productCount"
                            :per-page="productPageSize"
                            @click="retrieveAdminProduct"
                          ></b-pagination>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 끝 -->
                  </div>
                </div>
              </div>
              <!-- /.container-fluid -->
            </div>

            <!-- TODO: 상품 상세정보 이미지 시작 -->
            <!-- 좌측중단 검색 -->
              <form
                class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
              >
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control bg-light border-0 small"
                    placeholder="상품이미지 검색"
                    aria-label="Search"
                    aria-describedby="basic-addon2"
                    v-model="searchPdid"
                  />
                  <div class="input-group-append">
                    <button
                      class="btn btn-primary"
                      type="button"
                      @click="retrieveAdminProductImage"
                    >
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
            <div class="container-fluid">
              <!-- Page Heading -->
              <h1 class="h3 mb-2 mt-3 text-gray-800">상품이미지관리</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">상품이미지관리</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <div v-if="!submitted">
                      <!-- TODO: 등록 시작 -->
                      <button
                        type="button"
                        class="btn btn-primary mr-3 mb-3"
                        data-bs-toggle="modal"
                        :data-bs-target="'#exampleModal-2'"
                      >
                        등록
                      </button>

                      <!-- 등록 모달 -->
                      <div class="modal fade" id="exampleModal-2" tabindex="-1">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h1
                                class="modal-title fs-5"
                                id="exampleModalLabel"
                              >
                                옵션 등록
                              </h1>
                              <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                              ></button>
                            </div>
                            <!-- 등록 모달 내 목록 -->
                            <div class="modal-body">
                              <!-- 회원아이디 -->
                              <h3 class="fs-5 mt-1">회원아이디</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                disabled
                                type="text"
                                class="form-control mb-4"
                                name="text"
                                v-model="adminProductImageData.userId"
                              />
                              <!-- 상품번호 -->
                              <h3 class="fs-5 mt-1">상품번호</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="상품번호를 입력하세요"
                                name="text"
                                v-model="adminProductImageData.pdId"
                              />
                              <!-- 이미지 URL -->
                              <h3 class="fs-5 mt-1">이미지 URL</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="이미지 URL을 입력하세요"
                                name="text"
                                v-model="adminProductImageData.pdImgUrl"
                              />
                              <!-- 상태 확인 시작 -->
                              <h3 class="fs-5 mt-3">상태</h3>
                              <div class="form-check form-check-inline mt-3">
                                <label
                                  for="form-check-label"
                                  class="inlineRadio1"
                                ></label>
                                <input
                                  type="radio"
                                  class="form-check-input mb-4"
                                  name="Y"
                                  value="활성"
                                  v-model="adminProductImageData.status"
                                />
                                <label
                                  class="form-check-label"
                                  for="inlineRadio1"
                                  >활성</label
                                >
                              </div>
                              <div class="form-check form-check-inline">
                                <label
                                  for="form-check-label"
                                  class="inlineRadio2"
                                ></label>
                                <input
                                  type="radio"
                                  class="form-check-input mb-4"
                                  name="N"
                                  value="비활성"
                                  v-model="adminProductImageData.status"
                                />
                                <label
                                  class="form-check-label"
                                  for="inlineRadio2"
                                  >비활성</label
                                >
                              </div>
                              <!-- 상태 확인 끝 -->
                            </div>
                            <div class="modal-footer">
                              <button
                                type="button"
                                class="btn btn-secondary"
                                data-bs-dismiss="modal"
                              >
                                닫기
                              </button>
                              <button
                                type="submit"
                                class="btn btn-primary"
                                @click="saveAdminProductImageData"
                              >
                                등록
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- 등록 모달 끝 -->

                      <!-- TODO: 등록 끝 -->
                      <div class="row">
                        <div class="col-sm-12">
                          <table
                            class="table table-bordered dataTable"
                            width="100%"
                            cellspacing="0"
                            role="grid"
                            aria-describedby="dataTable_info"
                            style="width: 100%"
                          >
                            <thead>
                              <tr role="row">
                                <th>액션</th>
                                <th>상품이미지번호</th>
                                <th>상품번호</th>
                                <th>이미지 URL</th>
                                <th>저장일</th>
                                <th>수정일</th>
                                <th>삭제일</th>
                                <th>상태</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="(data, index) in productImage"
                                :key="index"
                              >
                              <td>
                                  <router-link
                                    :to="'/admin-product-image-edit/' + data.pdImgId"
                                  >
                                    <span class="badge text-bg-secondary"
                                      >수정</span
                                    >
                                  </router-link>
                                </td>
                                <td>{{ data.pdImgId }}</td>
                                <td>{{ data.pdId }}</td>
                                <td>{{ data.pdImgUrl }}</td>
                                <td>{{ data.addDate }}</td>
                                <td>{{ data.modDate }}</td>
                                <td>{{ data.delDate }}</td>
                                <td>{{ data.status }}</td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 시작 -->
                    <div class="row">
                      <div class="col-sm-12 col-md-5">
                        <div
                          class="dataTables_info"
                          role="status"
                          aria-live="polite"
                        >
                          검색결과 총 {{ productImageCount }} 건
                        </div>
                      </div>
                      <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_Simple_numbers">
                          <b-pagination
                            v-model="productImagePage"
                            :total-rows="productImageCount"
                            :per-page="productImagePageSize"
                            @click="retrieveAdminProductImage"
                          ></b-pagination>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 끝 -->
                  </div>
                </div>
              </div>
              <!-- /.container-fluid -->
            </div>
            <!-- TODO: 상품 상세정보 이미지 끝 -->

            <!-- TODO: 상품옵션 시작 -->
            <!-- 좌측중단 검색 -->
              <form
                class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"
              >
                <div class="input-group">
                  <input
                    type="text"
                    class="form-control bg-light border-0 small"
                    placeholder="옵션관리 검색"
                    aria-label="Search"
                    aria-describedby="basic-addon2"
                    v-model="searchOpName"
                  />
                  <div class="input-group-append">
                    <button
                      class="btn btn-primary"
                      type="button"
                      @click="retrieveAdminOption"
                    >
                      <i class="fas fa-search fa-sm"></i>
                    </button>
                  </div>
                </div>
              </form>
            <div class="container-fluid">
              <!-- Page Heading -->
              <h1 class="h3 mb-2 mt-3 text-gray-800">옵션관리</h1>
              <br />

              <!-- DataTales Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">옵션관리</h6>
                </div>
                <div class="card-body">
                  <div class="table-responsive">
                    <div v-if="!submitted">
                      <!-- TODO: 등록 시작 -->
                      <button
                        type="button"
                        class="btn btn-primary mr-3 mb-3"
                        data-bs-toggle="modal"
                        :data-bs-target="'#exampleModal-2'"
                      >
                        등록
                      </button>

                      <!-- 등록 모달 -->
                      <div class="modal fade" id="exampleModal-2" tabindex="-1">
                        <div class="modal-dialog">
                          <div class="modal-content">
                            <div class="modal-header">
                              <h1
                                class="modal-title fs-5"
                                id="exampleModalLabel"
                              >
                                옵션 등록
                              </h1>
                              <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                              ></button>
                            </div>
                            <!-- 등록 모달 내 목록 -->
                            <div class="modal-body">
                              <!-- 회원아이디 -->
                              <h3 class="fs-5 mt-1">회원아이디</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                disabled
                                type="text"
                                class="form-control mb-4"
                                name="text"
                                v-model="adminOptionData.userId"
                              />
                              <!-- 상품번호 -->
                              <h3 class="fs-5 mt-1">상품번호</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="상품번호를 입력하세요"
                                name="text"
                                v-model="adminOptionData.pdId"
                              />
                              <!-- 옵션명 -->
                              <h3 class="fs-5 mt-1">옵션명</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="옵션명을 입력하세요"
                                name="text"
                                v-model="adminOptionData.opName"
                              />
                              <!-- 옵션추가가격 -->
                              <h3 class="fs-5 mt-3">옵션추가 가격</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="옵션추가 가격을 입력하세요"
                                name="text"
                                v-model="adminOptionData.opPrice"
                              />
                              <!-- 옵션 재고 -->
                              <h3 class="fs-5 mt-3">옵션 재고</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="옵션재고수량을 입력하세요"
                                name="text"
                                v-model="adminOptionData.opStock"
                              />
                              <!-- 옵션분류코드 -->
                              <h3 class="fs-5 mt-3">옵션분류코드</h3>
                              <label for="text" class="form-label"></label>
                              <input
                                type="text"
                                class="form-control mb-4"
                                placeholder="옵션분류코드를 입력하세요"
                                name="text"
                                v-model="adminOptionData.opCode"
                              />
                              <!-- 상태 확인 시작 -->
                              <h3 class="fs-5 mt-3">상태</h3>
                              <div class="form-check form-check-inline mt-3">
                                <label
                                  for="form-check-label"
                                  class="inlineRadio1"
                                ></label>
                                <input
                                  type="radio"
                                  class="form-check-input mb-4"
                                  name="Y"
                                  value="활성"
                                  v-model="adminOptionData.status"
                                />
                                <label
                                  class="form-check-label"
                                  for="inlineRadio1"
                                  >활성</label
                                >
                              </div>
                              <div class="form-check form-check-inline">
                                <label
                                  for="form-check-label"
                                  class="inlineRadio2"
                                ></label>
                                <input
                                  type="radio"
                                  class="form-check-input mb-4"
                                  name="N"
                                  value="비활성"
                                  v-model="adminOptionData.status"
                                />
                                <label
                                  class="form-check-label"
                                  for="inlineRadio2"
                                  >비활성</label
                                >
                              </div>
                              <!-- 상태 확인 끝 -->
                            </div>
                            <div class="modal-footer">
                              <button
                                type="button"
                                class="btn btn-secondary"
                                data-bs-dismiss="modal"
                              >
                                닫기
                              </button>
                              <button
                                type="submit"
                                class="btn btn-primary"
                                @click="saveAdminOptionData"
                              >
                                등록
                              </button>
                            </div>
                          </div>
                        </div>
                      </div>
                      <!-- 등록 모달 끝 -->

                      <!-- TODO: 등록 끝 -->
                      <div class="row">
                        <div class="col-sm-12">
                          <table
                            class="table table-bordered dataTable"
                            width="100%"
                            cellspacing="0"
                            role="grid"
                            aria-describedby="dataTable_info"
                            style="width: 100%"
                          >
                            <thead>
                              <tr role="row">
                                <th>액션</th>
                                <th>옵션번호</th>
                                <th>상품번호</th>
                                <th>옵션명</th>
                                <th>옵션추가가격</th>
                                <th>옵션재고</th>
                                <th>저장일</th>
                                <th>수정일</th>
                                <th>삭제일</th>
                                <th>옵션분류코드</th>
                                <th>상태</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr
                                v-for="(data, index) in adminOption"
                                :key="index"
                              >
                              <td>
                                  <router-link
                                    :to="'/admin-option-edit/' + data.opId"
                                  >
                                    <span class="badge text-bg-secondary"
                                      >수정</span
                                    >
                                  </router-link>
                                </td>
                                <td>{{ data.opId }}</td>
                                <td>{{ data.pdId }}</td>
                                <td>{{ data.opName }}</td>
                                <td>{{ data.opPrice }}</td>
                                <td>{{ data.opStock }}</td>
                                <td>{{ data.addDate }}</td>
                                <td>{{ data.modDate }}</td>
                                <td>{{ data.delDate }}</td>
                                <td>{{ data.opCode }}</td>
                                <td>{{ data.status }}</td>
                              </tr>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 시작 / 페이지 네이션 백에서 해놓음 -->
                    <div class="row">
                      <div class="col-sm-12 col-md-5">
                        <div
                          class="dataTables_info"
                          role="status"
                          aria-live="polite"
                        >
                          검색결과 총 {{ optionCount }} 건
                        </div>
                      </div>
                      <div class="col-sm-12 col-md-7">
                        <div class="dataTables_paginate paging_Simple_numbers">
                          <b-pagination
                            v-model="optionPage"
                            :total-rows="optionCount"
                            :per-page="optionPageSize"
                            @click="retrieveAdminOption"
                          ></b-pagination>
                        </div>
                      </div>
                    </div>
                    <!-- TODO: 페이지 끝 -->
                  </div>
                </div>
              </div>
              <!-- /.container-fluid -->
            </div>
          </div>
          <!-- TODO: 게시판 끝 -->

          <!-- TODO: Footer 부분   -->
          <footer class="sticky-footer bg-white">
            <div class="container my-auto">
              <div class="logo text-center my-auto">
                <span>서울쥐</span>
              </div>
            </div>
          </footer>
          <!-- End of Footer -->
        </div>
        <!-- End of Content Wrapper -->
      </div>
      <!-- End of Page Wrapper -->

      <!-- TODO: 맨 위 스크롤 버튼-->
      <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
      </a>
    </body>
  </div>
</template>
<script>
import AdminProuctService from "@/services/admin/AdminProductService";
import AdminProductImageService from "@/services/admin/AdminProductImageService";
import AdminOptionService from "@/services/admin/AdminOptionService";
export default {
  data() {
    return {
      // TODO: 상품 등록
      adminProductData: {
        userId: this.$store.state.user.userId, // 관리자 ID
      },
      adminProductImageData: {
        userId: this.$store.state.user.userId,
      },
      adminOptionData: {
        userId: this.$store.state.user.userId,
      },
      submitted: false, // 저장버튼 클릭하면 true 바뀜

      // TODO: 백엔드 연결
      adminProduct: [], // spring 에서 전송
      productImage: [],
      adminOption: [],
      searchPdName: "", // 상품명
      searchPdid: "", // 상품번호
      searchOpName: "", // 옵션명

      productPage: 1, // 현재페이지번호
      productImagePage: 1,
      optionPage: 1,
      productCount: 0, // 전체데이터개수
      productImageCount: 0,
      optionCount: 0,
      productPageSize: 10, // 1페이지당개수(select태그)
      productImagePageSize: 10,
      optionPageSize: 10,

      pageSizes: [10, 25, 50], //1페이지당개수 배열(select태그-option)
    };
  },
  methods: {
    // TODO: 상품 등록 시작
    async saveAdminProductData() {
      try {
        // 임시 객체 변수
        let data = {
          // 상품 관리
          pdId: this.adminProductData.pdId,
          userId: this.adminProductData.userId,
          pdName: this.adminProductData.pdName,
          pdPrice: this.adminProductData.pdPrice,
          pdStock: this.adminProductData.pdStock,
          categoryCode: this.adminProductData.categoryCode,
          pdThumbnail: this.adminProductData.pdThumbnail,
          addDate: this.adminProductData.addDate,
          modDate: this.adminProductData.modDate,
          delDate: this.adminProductData.delDate,
          status: this.adminProductData.status,
        };
        console.log(data);
        // TODO: 벡엔드로 객체 추가 요청
        let response = await AdminProuctService.create(data);
        // TODO: 콘솔에 결과 출력
        console.log(response);
        this.submitted = true; // 저장유무변수=true 변경
      } catch (e) {
        console.log(e);
      }
    },
    // 저장페이지 열기 함수 : 화면 초기화
    newAdminProductData() {
      this.submitted = false;
      this.adminProductData = {};
    },
    // TODO: 상품 등록 끝
    // TODO: 상품 이미지 등록 시작
    async saveAdminProductImageData() {
      try {
        let data = {
          // 상품 이미지 관리
          pdImgId: this.adminProductImageData.pdImgId,
          pdId: this.adminProductImageData.pdId,
          pdImgUrl: this.adminProductImageData.pdImgUrl,
          addDate: this.adminProductImageData.addDate,
          modDate: this.adminProductImageData.modDate,
          delDate: this.adminProductImageData.delDate,
          status: this.adminProductImageData.status,
        };
        console.log(data);
        // TODO: 벡엔드로 객체 추가 요청
        let response = await AdminProductImageService.create(data);
        // TODO: 콘솔에 결과 출력
        console.log(response);
        this.submitted = true;
      } catch (e) {
        console.log(e);
      }
    },
    newAdminProductImageData() {
      this.submitted = false;
      this.adminProductImageData = {};
    },
    // TODO: 상품 이미지 등록 끝
    // TODO: 상품 옵션 등록 시작
    async saveAdminOptionData() {
      alert("확인");
      try {
        let optionData = {
          opId: this.adminOptionData.opId,
          pdId: this.adminOptionData.pdId,
          opName: this.adminOptionData.opName,
          opPrice: this.adminOptionData.opPrice,
          opStock: this.adminOptionData.opStock,
          opCode: this.adminOptionData.opCode,
        };
        console.log("옵션저장",optionData);
        let response = await AdminOptionService.create(optionData);
        console.log(response);
        this.$router.push("/admin-product");
      } catch (e) {
        console.log(e);
      }
    },
    newAdminOptionData() {
      this.submitted = false;
      this.adminOptionData = {};
    },
    // TODO: 상품 옵션 등록 끝
    // TODO: 백엔드 연결
    pageNoChange(value) {
      this.productPage = value; // 1) 현재페이지 변경
      this.productImagePage = value;
      this.optionPage = value;
      this.retrieveAdminProduct(); // 2) 재조회 요청
      this.retrieveAdminProductImage();
      this.retrieveAdminOption();
    },
    pageSizeChange() {
      this.productPage = 1; // 2) 현재 페이지번호 초기화(1)
      this.productImagePage = 1;
      this.optionPage = 1;
      this.retrieveAdminProduct(); // 3) 재조회 요청
      this.retrieveAdminProductImage();
      this.retrieveAdminOption();
    },
    async retrieveAdminProduct() {
      try {
        console.log("상품",this.productPageSize);
        let response = await AdminProuctService.getAll(
          this.searchPdName, // 검색어
          this.productPage - 1, // 현재페이지번호-1
          this.productPageSize // 1페이지당개수(size)
        );
        const { adminProduct, totalItems } = response.data; // 배열(벡엔드 전송)
        this.adminProduct = adminProduct; // 배열(벡엔드 전송)
        this.productCount = totalItems; // 전체페이지수(벡엔드 전송)
        console.log("전체조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveAdminProductImage() {
      try {
        console.log("상품이미지",this.productImagePageSize);
        let response = await AdminProductImageService.getAll(
          this.searchPdid,
          this.productImagePage - 1,
          this.productImagePageSize
        );
        const { productImage, totalItems } = response.data;
        this.productImage = productImage;
        this.productImageCount = totalItems;
        console.log("전체조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    async retrieveAdminOption() {
      try {
        let response = await AdminOptionService.getAll(
          this.searchOpName,
          this.optionPage - 1,
          this.optionPageSize
        );
        const { adminOption, totalItems } = response.data;
        this.adminOption = adminOption;
        this.optionCount = totalItems;
        console.log("전체조회", response.data);
      } catch (e) {
        console.log(e);
      }
    },
    // TODO: 백엔드 끝
    // TODO: 백엔드 시작
  },
  mounted() {
    this.retrieveAdminProduct(); // 전체 조회 함수 실행
    this.retrieveAdminProductImage();
    this.retrieveAdminOption();
    // TODO: 백엔드 끝
  },
};
</script>
<style>
@font-face {
  font-family: "YClover-Bold";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_231029@1.1/YClover-Bold.woff2")
    format("woff2");
  font-weight: 700;
  font-style: normal;
}
.logo {
  /* background-color: rgb(115, 235, 67); */
  font-size: 30px;
  font-family: "YClover-Bold";
}
.table th {
  white-space: nowrap; /* 줄바꿈 방지 */
  text-overflow: ellipsis; /* 텍스트 생략 */
  padding: 0 30px; /* 좌우 여백 추가 */
}
.table td {
  text-align: center; /* 가운데 정렬 */
}
</style>
