/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NuoTestModule } from '../../../test.module';
import { CrowdfundingWalletMySuffixComponent } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.component';
import { CrowdfundingWalletMySuffixService } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.service';
import { CrowdfundingWalletMySuffix } from '../../../../../../main/webapp/app/entities/crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.model';

describe('Component Tests', () => {

    describe('CrowdfundingWalletMySuffix Management Component', () => {
        let comp: CrowdfundingWalletMySuffixComponent;
        let fixture: ComponentFixture<CrowdfundingWalletMySuffixComponent>;
        let service: CrowdfundingWalletMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [CrowdfundingWalletMySuffixComponent],
                providers: [
                    CrowdfundingWalletMySuffixService
                ]
            })
            .overrideTemplate(CrowdfundingWalletMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(CrowdfundingWalletMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CrowdfundingWalletMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new CrowdfundingWalletMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.crowdfundingWallets[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
