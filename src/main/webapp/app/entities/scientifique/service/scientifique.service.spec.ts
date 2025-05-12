import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { IScientifique } from '../scientifique.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../scientifique.test-samples';

import { ScientifiqueService } from './scientifique.service';

const requireRestSample: IScientifique = {
  ...sampleWithRequiredData,
};

describe('Scientifique Service', () => {
  let service: ScientifiqueService;
  let httpMock: HttpTestingController;
  let expectedResult: IScientifique | IScientifique[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(ScientifiqueService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Scientifique', () => {
      const scientifique = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(scientifique).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Scientifique', () => {
      const scientifique = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(scientifique).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Scientifique', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Scientifique', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Scientifique', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addScientifiqueToCollectionIfMissing', () => {
      it('should add a Scientifique to an empty array', () => {
        const scientifique: IScientifique = sampleWithRequiredData;
        expectedResult = service.addScientifiqueToCollectionIfMissing([], scientifique);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(scientifique);
      });

      it('should not add a Scientifique to an array that contains it', () => {
        const scientifique: IScientifique = sampleWithRequiredData;
        const scientifiqueCollection: IScientifique[] = [
          {
            ...scientifique,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addScientifiqueToCollectionIfMissing(scientifiqueCollection, scientifique);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Scientifique to an array that doesn't contain it", () => {
        const scientifique: IScientifique = sampleWithRequiredData;
        const scientifiqueCollection: IScientifique[] = [sampleWithPartialData];
        expectedResult = service.addScientifiqueToCollectionIfMissing(scientifiqueCollection, scientifique);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(scientifique);
      });

      it('should add only unique Scientifique to an array', () => {
        const scientifiqueArray: IScientifique[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const scientifiqueCollection: IScientifique[] = [sampleWithRequiredData];
        expectedResult = service.addScientifiqueToCollectionIfMissing(scientifiqueCollection, ...scientifiqueArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const scientifique: IScientifique = sampleWithRequiredData;
        const scientifique2: IScientifique = sampleWithPartialData;
        expectedResult = service.addScientifiqueToCollectionIfMissing([], scientifique, scientifique2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(scientifique);
        expect(expectedResult).toContain(scientifique2);
      });

      it('should accept null and undefined values', () => {
        const scientifique: IScientifique = sampleWithRequiredData;
        expectedResult = service.addScientifiqueToCollectionIfMissing([], null, scientifique, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(scientifique);
      });

      it('should return initial array if no Scientifique is added', () => {
        const scientifiqueCollection: IScientifique[] = [sampleWithRequiredData];
        expectedResult = service.addScientifiqueToCollectionIfMissing(scientifiqueCollection, undefined, null);
        expect(expectedResult).toEqual(scientifiqueCollection);
      });
    });

    describe('compareScientifique', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareScientifique(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareScientifique(entity1, entity2);
        const compareResult2 = service.compareScientifique(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareScientifique(entity1, entity2);
        const compareResult2 = service.compareScientifique(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareScientifique(entity1, entity2);
        const compareResult2 = service.compareScientifique(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
