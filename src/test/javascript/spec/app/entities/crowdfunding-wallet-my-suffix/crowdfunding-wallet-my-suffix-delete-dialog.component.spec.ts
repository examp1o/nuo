/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { NuoTestModule } from '../../../test.module';
import { CrowdfundingWalletMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix-delete-dialog.component';
import { CrowdfundingWalletMySuffixService } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.service';

describe('Component Tests', () => {

    describe('CrowdfundingWalletMySuffix Management Delete Component', () => {
        let comp: CrowdfundingWalletMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<CrowdfundingWalletMySuffixDeleteDialogComponent>;
        let service: CrowdfundingWalletMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [CrowdfundingWalletMySuffixDeleteDialogComponent],
                providers: [
                    CrowdfundingWalletMySuffixService
                ]
            })
            .overrideTemplate(CrowdfundingWalletMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CrowdfundingWalletMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CrowdfundingWalletMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
